package com.example.taskvietsoft.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class JwtHelPer {

    @Value("${jwt.privatekey}")
    private String stringKey;

    private String keystoreFilepath = "keystore.p12"; // Nếu file nằm trong src/main/resources


    @Value("${server.ssl.key-store-password}")
    private String keystorePassword;

    @Value("${server.ssl.key-alias}")
    private String keystoreAlias;

    @Value("${jwt.algorithm}")  // Cấu hình loại thuật toán (HMAC/RSA)
    private String algorithm;

    // Hàm sinh JWT sử dụng HMAC (mã hóa đối xứng)
    public String genToKen(String userName, List<String> roles,long expirationMillis) {
        if ("RSA".equalsIgnoreCase(algorithm)) {
            return genTokenWithRSA(userName, roles);  // Sử dụng RSA với username và roles
        } else {
            return genTokenWithHMAC(userName, roles,expirationMillis); // Sử dụng HMAC với username và roles
        }
    }


    // Hàm sinh JWT sử dụng HMAC (mã hóa đối xứng)
    private String genTokenWithHMAC(String data,List<String> roles,long expirationMillis) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(stringKey));
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationMillis);
        String jws = Jwts.builder()
                .setSubject(data)
                .claim("roles", roles)
                .setExpiration(expirationDate) //
                .signWith(key)
                .compact();
        System.out.println("Token (HMAC): " + jws);
        return jws;
    }

    // Hàm sinh JWT sử dụng RSA (private key để ký)
    private String genTokenWithRSA(String userName, List<String> roles) {
        try (InputStream keyStoreStream = getClass().getClassLoader().getResourceAsStream(keystoreFilepath)) {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            if (keyStoreStream == null) {
                throw new RuntimeException("Keystore file not found.");
            }
            keyStore.load(keyStoreStream, keystorePassword.toCharArray());

            // Lấy Private Key từ keystore
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(keystoreAlias, keystorePassword.toCharArray());

            // Tạo và ký JWT bằng RSA, thêm thông tin về userName và roles
            String jws = Jwts.builder()
                    .setSubject(userName) // Thêm userName vào subject
                    .claim("roles", roles) // Thêm roles vào payload
                    .signWith(privateKey)
                    .compact();

            System.out.println("Token (RSA): " + jws); // In ra token để kiểm tra
            return jws;

        } catch (Exception e) {
            throw new RuntimeException("Error generating token with RSA", e);
        }
    }


    // Hàm xác thực JWT sử dụng HMAC (mã hóa đối xứng)
    public Boolean verifyToken(String token) {
        if ("RSA".equalsIgnoreCase(algorithm)) {
            return verifyTokenWithRSA(token);  // Sử dụng RSA để xác thực
        } else {
            return verifyTokenWithHMAC(token); // Sử dụng HMAC để xác thực
        }
    }

    // Hàm xác thực JWT sử dụng HMAC (mã hóa đối xứng)
    private Boolean verifyTokenWithHMAC(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(stringKey));
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseSignedClaims(token);
            return true;

        } catch (JwtException e) {
            return false;
        }
    }

    // Hàm xác thực JWT sử dụng RSA (Mã Hóa Bất Đối Xứng)
    public Boolean verifyTokenWithRSA(String token) {
        try {
            // Lấy public key từ keystore
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            try (InputStream keyStoreStream = getClass().getClassLoader().getResourceAsStream(keystoreFilepath)) {
                if(keyStoreStream==null){
                    throw new FileNotFoundException("File not foud");
                }
                keyStore.load(keyStoreStream, keystorePassword.toCharArray());
            }

            PublicKey publicKey = keyStore.getCertificate(keystoreAlias).getPublicKey();

            // Xác thực token bằng RSA public key
            Jwts.parser()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
    public List<String> extractRoles(String token) {
        try {
            Claims claims;
            if ("RSA".equalsIgnoreCase(algorithm)) {
                // Xác thực và giải mã token bằng RSA Public Key
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                try (InputStream keyStoreStream = getClass().getClassLoader().getResourceAsStream(keystoreFilepath)) {
                    if (keyStoreStream == null) {
                        throw new FileNotFoundException("Keystore file not found.");
                    }
                    keyStore.load(keyStoreStream, keystorePassword.toCharArray());
                }
                PublicKey publicKey = keyStore.getCertificate(keystoreAlias).getPublicKey();

                // Parse token với publicKey
                claims = Jwts.parser()
                        .setSigningKey(publicKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                System.out.println("Claims: " + claims);

            } else {
                // Xác thực và giải mã token bằng HMAC Secret Key
                SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(stringKey));
                claims = Jwts.parser()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
            }

            // Trích xuất danh sách roles từ claims
            Object rolesObj = claims.get("roles");  // Lấy giá trị "roles" từ claims

            if (rolesObj instanceof List<?>) {
                // Nếu roles là một danh sách, ép kiểu thành List<String>
                return (List<String>) rolesObj;
            } else if (rolesObj instanceof String) {
                // Nếu roles là một chuỗi, tạo một danh sách với một phần tử là chuỗi này
                return Collections.singletonList((String) rolesObj);
            } else {
                // Nếu không phải danh sách hay chuỗi, trả về một danh sách rỗng hoặc có thể throw Exception tùy vào yêu cầu của bạn
                return new ArrayList<>();
            }

        } catch (Exception e) {
            throw new RuntimeException("Error extracting roles from JWT", e);
        }
    }




}
