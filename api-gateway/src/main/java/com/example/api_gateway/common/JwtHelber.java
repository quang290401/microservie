package com.example.api_gateway.common;

import ch.qos.logback.classic.spi.IThrowableProxy;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component // Đánh dấu class này là một bean Spring, Spring sẽ tự động phát hiện và quản lý đối tượng này.
public class JwtHelber {

    @Value("${jwt.privatekey}") // Lấy giá trị private key từ file cấu hình (application.properties hoặc application.yml).
    private String stringKey; // Khóa bí mật dùng để giải mã JWT.

    // Phương thức này giải mã và xác thực token, trả về Claims (thông tin từ token).
    public Claims decodeAndVerifyToken(String token) {
        try {
            // Tạo SecretKey từ chuỗi stringKey (base64-decoded)
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(stringKey));

            // Giải mã và xác thực token
            return Jwts.parser()
                    .setSigningKey(key) // Thiết lập khóa ký để xác thực
                    .build()
                    .parseClaimsJws(token) // Phân tích token JWT đã ký
                    .getBody(); // Lấy phần body của token (chứa Claims)

        } catch (JwtException e) { // Nếu có lỗi trong việc xác thực hoặc giải mã token
            System.err.println("Invalid token: " + e.getMessage()); // Ghi lỗi nếu token không hợp lệ
            return null; // Trả về null nếu không thể giải mã token
        }
    }

    // Phương thức này lấy thông tin role từ token (claims).
    public Claims getRoleFromToken(String token) {
        Claims claims = decodeAndVerifyToken(token); // Giải mã token và lấy Claims
        if (claims != null) { // Nếu giải mã thành công
            return claims; // Trả về Claims chứa thông tin role (hoặc các thông tin khác).
        }
        return null; // Nếu không có claims, trả về null.
    }
}
