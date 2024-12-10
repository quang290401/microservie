package com.example.duantest.service;



import com.example.duantest.dto.LoaiSanPhamDTO;

import java.util.List;

public interface LoaiSanPhamService {
    List<LoaiSanPhamDTO> getAllLoaiSanPham();
    LoaiSanPhamDTO addLoaiSanPham(LoaiSanPhamDTO loaiSanPhamDTO);
    LoaiSanPhamDTO updateLoaiSanPham(LoaiSanPhamDTO loaiSanPhamDTO);

}
