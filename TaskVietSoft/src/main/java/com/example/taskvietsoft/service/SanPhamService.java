package com.example.taskvietsoft.service;
import com.example.taskvietsoft.dto.SanPhamDTO;

import java.util.List;
//Interface cung cấp các dịch vụ liên quan đến san pham
public interface SanPhamService {
   //trả về 1 sản phẩm dưa theo id
    SanPhamDTO findById(Long id);
    //trả về 1 danh sách sản phẩm dưa theo idDanhMuc
    List<SanPhamDTO> getSanPhamChiTietByDanhMuc(Long id, Integer pageNo, Integer pageSize);
}
