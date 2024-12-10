package com.example.securitytest.common;

public class AppCommon {

    // Định nghĩa thuật toán mã hóa AES
    public static final String AES = "AES";

    public static final String RSA = "RSA";
    public static final Integer KEY_SIZE = 2048;

    // Khóa bí mật (SECRET_KEY) dài 16 bytes (128 bits), dùng cho mã hóa AES
    // Lưu ý: Đây chỉ là một khóa mẫu, trong thực tế bạn nên thay thế bằng khóa bí mật mạnh hơn
    public static final byte[] SECRET_KEY = "1234567890123456".getBytes(); // Thay bằng khóa bí mật của bạn

}