package com.example.securitytest.common;


// Đóng gói dữ liệu phản hồi từ API,
// bao gồm dữ liệu chính (data), thông báo (message), và trạng thái (status)
public class ResponseWrapper<T> {
    private T data;
    private String message;
    private String status;

    // Constructor cho dữ liệu và thông báo
    public ResponseWrapper(T data, String message) {
        this.data = data;
        this.message = message;
        this.status = "success";  // Mặc định là "success"
    }

    // Constructor đầy đủ với trạng thái
    public ResponseWrapper(T data, String message, String status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }



    // Getter và Setter
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
