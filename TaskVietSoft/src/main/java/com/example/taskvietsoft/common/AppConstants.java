package com.example.taskvietsoft.common;

public class AppConstants {
    // Định nghĩa các hằng số mặc định cho số trang, tổng số
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_TOTAL_NUMBER = "5";

    //thông tin cấu hình kết nối đên Cloudinary
    public static final String DEFAULT_CLOUD_NAME = "dsxma8gfm";
    public static final String DEFAULT_API_KEY = "129679929512287";
    public static final String DEFAULT_API_SECRET = "z484pp3GRw_AIRKRmrTjKkW1jwo";
    // Kiểm tra loại tệp (chỉ chấp nhận .jpg hoặc .png)
    public static final String DEFAULT_IMAGE_JPG = "image/jpeg";
    public static final String DEFAULT_IMAGE_PNG = "image/png";
    // Giới hạn kích thước tệp (ví dụ: 2MB)
    public static final Long DEFAULT_MAX_FILE_SIZE = (long) (2 * 1024 * 1024);
    // Đảm bảo ảnh không vượt quá kích thước cố định
    public static final Integer DEFAULT_MAX_WITH = 500;
    public static final Integer DEFAULT_MAX_HEIGHT = 500;
    public static final String DEFAULT_CROP = "limit";
    public static final String DEFAULT_RESOURCE_TYPE = "auto";

//Quền truy câp
    public static final String DEFAULT_USER_ROLE= "USER";

    public static final String DEFAULT_ADMIN_ROLE= "ADMIN";





    // Ngăn không cho khởi tạo class
    private AppConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

