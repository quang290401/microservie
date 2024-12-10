package com.example.securitytest.common;

import javax.crypto.Cipher;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Lớp này cung cấp các phương thức mã hóa và giải mã dữ liệu bằng thuật toán AES với một khóa bí mật cố định.
 * AES (Advanced Encryption Standard) là một thuật toán mã hóa đối xứng rất phổ biến.
 *
 * Khóa bí mật (SECRET_KEY) được sử dụng để mã hóa và giải mã dữ liệu. Đây là một khóa cố định, trong thực tế,
 * bạn sẽ cần phải bảo vệ và quản lý khóa bí mật này một cách an toàn.
 */
public class AESUtils {
    /**
     * Mã hóa một chuỗi dữ liệu sử dụng thuật toán AES với khóa bí mật.
     *
     * @param data chuỗi dữ liệu cần mã hóa
     * @return chuỗi đã mã hóa dưới dạng Base64
     * @throws Exception nếu có lỗi trong quá trình mã hóa
     */
    public static String encrypt(String data) throws Exception {
        // Khởi tạo đối tượng Cipher với thuật toán AES
        Cipher cipher = Cipher.getInstance(AppCommon.AES);
        // Tạo khóa bí mật từ mảng byte SECRET_KEY
        SecretKey secretKey = new SecretKeySpec(AppCommon.SECRET_KEY, AppCommon.AES);
        // Khởi tạo cipher với chế độ mã hóa (ENCRYPT_MODE) và khóa bí mật
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // Mã hóa dữ liệu và trả về kết quả dưới dạng Base64
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    /**
     * Giải mã một chuỗi dữ liệu đã mã hóa sử dụng thuật toán AES với khóa bí mật.
     *
     * @param encryptedData chuỗi dữ liệu đã mã hóa cần giải mã
     * @return chuỗi dữ liệu gốc sau khi giải mã
     * @throws Exception nếu có lỗi trong quá trình giải mã
     */
    public static String decrypt(String encryptedData) throws Exception {
        // Khởi tạo đối tượng Cipher với thuật toán AES
        Cipher cipher = Cipher.getInstance(AppCommon.AES);
        // Tạo khóa bí mật từ mảng byte SECRET_KEY
        SecretKey secretKey = new SecretKeySpec(AppCommon.SECRET_KEY, AppCommon.AES);
        // Khởi tạo cipher với chế độ giải mã (DECRYPT_MODE) và khóa bí mật
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // Giải mã dữ liệu và trả về kết quả dưới dạng chuỗi
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }
}


