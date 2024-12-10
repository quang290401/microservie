package com.example.taskvietsoft.service;

import com.example.taskvietsoft.dto.HinhAnhDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//Interface cung cấp các dịch vụ liên quan đến hinh ảnh
public interface HinhAnhService {
    // upload hình ảnh và lưu đường dẫn vào database
    HinhAnhDTO upLoadAnh(String fath,Long idSanPham);
    //upload (tải lên) một tệp hình ảnh lên dịch vụ lưu trữ đám mây (Cloudinary)
    //trả về URL của hình ảnh đã được tải lên.
    String uploadImage(MultipartFile file) throws IOException;
}
