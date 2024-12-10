package com.example.duantest.service;


import com.example.duantest.dto.FiterDTO.SanPhamFiterDTO;
import com.example.duantest.dto.SanPhamCRUD;
import com.example.duantest.dto.SanPhamDTO;
import org.springframework.data.domain.Page;

public interface SanPhamService {
    Page<SanPhamDTO> getAllSanPham(Integer totalPage, Integer totalItem, SanPhamFiterDTO form);
    SanPhamCRUD addSanPham(SanPhamCRUD sanPhamCRUD);

    SanPhamCRUD updateSanPham(SanPhamCRUD sanPhamCRUD);
}
