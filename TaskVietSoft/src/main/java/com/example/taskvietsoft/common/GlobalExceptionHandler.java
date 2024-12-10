package com.example.taskvietsoft.common;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.IOException;



/**
 * Lớp GlobalExceptionHandler dùng để xử lý ngoại lệ chung cho toàn bộ ứng dụng.
 * Giúp đơn giản hóa việc xử lý lỗi trong từng controller và trả về các phản hồi HTTP tương ứng.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Xử lý ngoại lệ khi không tìm thấy thực thể trong cơ sở dữ liệu.
     *
     * @param ex Ngoại lệ EntityNotFoundException
     * @return ResponseEntity với mã lỗi 404 NOT FOUND và thông báo lỗi
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>("Không tồn tại bản ghi: "+ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Xử lý các ngoại lệ khác

    /**
     * Xử lý ngoại lệ khi dữ liệu hoặc tham số yêu cầu không hợp lệ.
     * @param ex Ngoại lệ IllegalArgumentException hoặc IllegalStateException
     * @return ResponseEntity với mã lỗi 400 BAD REQUEST và thông báo lỗi
     */
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<String> handleBadRequestException(RuntimeException ex) {
        return new ResponseEntity<>("Yêu cầu không hợp lệ: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Xử lý ngoại lệ không mong muốn khác.
     * @param ex Ngoại lệ bất kỳ chưa được xử lý
     * @return ResponseEntity với mã lỗi 500 INTERNAL SERVER ERROR và thông báo lỗi
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return new ResponseEntity<>("Lỗi server : " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /**
     * Xử lý ngoại lệ khi phương thức HTTP không được hỗ trợ.
     *
     * @param ex Ngoại lệ HttpRequestMethodNotSupportedException
     * @return ResponseEntity với mã lỗi 405 METHOD NOT ALLOWED và thông báo lỗi
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>("Phương thức " + ex.getMethod() + " không được phép cho URL này.", HttpStatus.METHOD_NOT_ALLOWED);
    }
    /**
     * Xử lý ngoại lệ khi có lỗi về I/O, chẳng hạn lỗi đọc file hoặc lỗi kết nối mạng
     *
     * @param ex Ngoại lệ IOException
     * @return ResponseEntity với mã lỗi 500 INTERNAL SERVER ERROR và thông báo lỗi
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Lỗi khi xử lý file hoặc kết nối: " + ex.getMessage());
    }
// Xử lý lỗi 401 - Unauthorized
    /**
     * Xử lý ngoại lệ khi người dùng chưa xác thực.
     *
     * @param ex Ngoại lệ InsufficientAuthenticationException
     * @return ResponseEntity với mã lỗi 401 UNAUTHORIZED và thông báo lỗi
     */
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<String> handleUnauthorizedException(InsufficientAuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Bạn cần phải đăng nhập để truy cập tài nguyên này " + ex.getMessage());
    }

    // Xử lý lỗi 403 - Forbidden
    /**
     * Xử lý ngoại lệ khi người dùng không có quyền truy cập vào tài nguyên.
     *
     * @param ex Ngoại lệ AccessDeniedException
     * @return ResponseEntity với mã lỗi 403 FORBIDDEN và thông báo lỗi
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Bạn không có quyền truy cập tài nguyên này " + ex.getMessage());
    }

    // Các phương thức xử lý khác...
}


