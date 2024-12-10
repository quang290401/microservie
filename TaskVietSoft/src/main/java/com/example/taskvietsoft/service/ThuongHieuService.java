package com.example.taskvietsoft.service;


import com.example.taskvietsoft.dto.ThuongHieuDTO;
import org.springframework.data.domain.Page;

//Interface cung cấp các dịch vụ liên quan đến thương hieu
public interface ThuongHieuService {
    //  phương thức này trả về danh sách các thuong hiệu sản phẩm theo phân trang.
//      @param PageNo Số trang cần lấy dữ liệu. (Trang bắt đầu từ 0)
//      @param PageSize Số lượng sản phẩm trong mỗi trang.
//      @return Page<DanhMucDTO> - Danh sách các danh mục sản phẩm theo phân trang.
    Page<ThuongHieuDTO> getAll(Integer pageNo, Integer pageSize);
}
