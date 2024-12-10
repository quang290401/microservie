package com.example.securitytest.common;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter
public class AccountConverter implements AttributeConverter<String, String> {

    /**
     * Chuyển đổi dữ liệu từ giá trị trong entity (Java object) thành giá trị lưu trữ trong cơ sở dữ liệu.
     * Hàm này sẽ được gọi khi thực hiện lưu (persist) hoặc cập nhật (update) dữ liệu.
     *
     * @param attribute Dữ liệu dạng chuỗi (String) cần lưu trữ (ví dụ: số tài khoản).
     * @return Chuỗi đã được mã hóa để lưu vào cơ sở dữ liệu.
     * @throws RuntimeException Nếu xảy ra lỗi khi mã hóa.
     */
    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            // Mã hóa dữ liệu (số tài khoản) trước khi lưu vào cơ sở dữ liệu
            return AESUtils.encrypt(attribute);
        } catch (Exception e) {
            // Ném ngoại lệ RuntimeException nếu xảy ra lỗi khi mã hóa
            throw new RuntimeException("Failed to encrypt account", e);
        }
    }

    /**
     * Chuyển đổi dữ liệu từ giá trị lưu trữ trong cơ sở dữ liệu về dạng giá trị trong entity (Java object).
     * Hàm này sẽ được gọi khi đọc (fetch) dữ liệu từ cơ sở dữ liệu.
     *
     * @param dbData Dữ liệu đã được mã hóa lưu trong cơ sở dữ liệu.
     * @return Chuỗi đã được giải mã để sử dụng trong ứng dụng.
     * @throws RuntimeException Nếu xảy ra lỗi khi giải mã.
     */
    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            // Giải mã dữ liệu từ cơ sở dữ liệu trước khi đưa về ứng dụng
            return AESUtils.decrypt(dbData);
        } catch (Exception e) {
            // Ném ngoại lệ RuntimeException nếu xảy ra lỗi khi giải mã
            throw new RuntimeException("Failed to decrypt account", e);
        }
    }
}


