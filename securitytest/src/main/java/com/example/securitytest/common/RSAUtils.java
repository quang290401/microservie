package com.example.securitytest.common;

import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

/**
 * Utility class cung cấp các phương thức mã hóa và giải mã dữ liệu sử dụng thuật toán RSA.
 * Cung cấp các phương thức để tạo cặp khóa RSA, mã hóa và giải mã dữ liệu với khóa công khai và khóa riêng tư.
 */
@Configuration
public class RSAUtils {

    // Thuật toán RSA được sử dụng trong việc mã hóa và giải mã
    private static final String RSA_ALGORITHM = "RSA";


    /**
     * Mã hóa dữ liệu (chuỗi) bằng khóa công khai sử dụng thuật toán RSA.
     * Dữ liệu sau khi mã hóa sẽ được trả về dưới dạng chuỗi đã mã hóa, mã hóa theo định dạng Base64.
     *
     * @param data       dữ liệu cần mã hóa.
     * @param publicKey  khóa công khai để mã hóa dữ liệu.
     * @return dữ liệu đã mã hóa dưới dạng chuỗi Base64.
     * @throws Exception nếu có lỗi trong quá trình mã hóa.
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        // Khởi tạo đối tượng Cipher sử dụng thuật toán RSA
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);

        // Cấu hình cipher để mã hóa dữ liệu với khóa công khai
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Mã hóa dữ liệu và trả về kết quả dưới dạng Base64
        byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * Giải mã dữ liệu đã mã hóa bằng khóa riêng tư sử dụng thuật toán RSA.
     * Dữ liệu mã hóa sẽ được giải mã về lại dữ liệu gốc.
     *
     * @param encryptedData dữ liệu đã mã hóa cần giải mã.
     * @param privateKey    khóa riêng tư để giải mã dữ liệu.
     * @return dữ liệu đã được giải mã, dưới dạng chuỗi gốc.
     * @throws Exception nếu có lỗi trong quá trình giải mã.
     */
    public static String decrypt(String encryptedData, PrivateKey privateKey) throws Exception {
        // Khởi tạo đối tượng Cipher sử dụng thuật toán RSA
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        // Cấu hình cipher để giải mã dữ liệu với khóa riêng tư
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // Giải mã dữ liệu và trả về kết quả dưới dạng chuỗi gốc
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        String dbPassword = System.getenv("DB_PASSWORD");
        System.out.println("DB_PASSWORD from environment: " + dbPassword);
    }
}

