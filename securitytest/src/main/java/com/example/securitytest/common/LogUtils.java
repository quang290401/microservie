package com.example.securitytest.common;

import org.apache.commons.lang3.StringUtils;

public class LogUtils {

    /**
     * Phương thức này giúp ẩn bớt phần dữ liệu nhạy cảm, chỉ giữ lại một số ký tự cuối cùng của chuỗi để bảo vệ thông tin.
     * Dữ liệu sẽ được thay thế bằng dấu "?" cho đến khi chỉ còn lại số lượng ký tự cuối cùng có thể nhìn thấy (tối đa 3 ký tự).
     * Phương thức này giúp che giấu dữ liệu nhạy cảm khi cần log thông tin hoặc xử lý mà không tiết lộ dữ liệu đầy đủ.
     *
     * @param data chuỗi dữ liệu cần được ẩn bớt.
     * @return chuỗi dữ liệu đã được ẩn bớt, chỉ còn lại số lượng ký tự cuối cùng có thể nhìn thấy.
     */
    public static String maskSensitiveData(String data) {
        if (StringUtils.isBlank(data)) {
            return data; // Nếu dữ liệu rỗng, trả lại nguyên trạng
        }
        int visibleLength = Math.min(3, data.length()); // Hiển thị tối đa 3 ký tự
        return "?".repeat(data.length() - visibleLength) + data.substring(data.length() - visibleLength);
    }
}
