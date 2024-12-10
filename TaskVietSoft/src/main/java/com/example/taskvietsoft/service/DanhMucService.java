package com.example.taskvietsoft.service;

import com.example.taskvietsoft.dto.DanhMucDTO;
import org.springframework.data.domain.Page;
//Interface cung cấp các dịch vụ liên quan đến danh mục.
public interface DanhMucService {
    // Phương thức này trả về danh sách các danh mục sản phẩm theo phân trang.
    // @param pageNo Số trang cần lấy dữ liệu. (Trang bắt đầu từ 0)
    // @param pageSize Số lượng sản phẩm trong mỗi trang.
    // @return Page<DanhMucDTO> - Danh sách các danh mục sản phẩm theo phân trang.
    Page<DanhMucDTO> getAll(Integer pageNo, Integer pageSize);
}