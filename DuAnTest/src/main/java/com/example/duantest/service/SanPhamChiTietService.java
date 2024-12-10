package com.example.duantest.service;


import com.example.duantest.dto.FiterDTO.SanPhamFiterDTO;
import com.example.duantest.dto.SanPhamChiTietCRUD;
import com.example.duantest.dto.SanPhamChiTietDTO;
import org.springframework.data.domain.Page;

public interface SanPhamChiTietService {
    Page<SanPhamChiTietDTO> getAllSanPhamChiTietBYidSP(Long idSP, Integer totalPage, Integer totalItem, SanPhamFiterDTO fiterDTO);
    SanPhamChiTietCRUD addSanPham(SanPhamChiTietCRUD sanPhamChiTietCRUD);

    SanPhamChiTietCRUD updateSanPham(SanPhamChiTietCRUD sanPhamChiTietCRUD);;
}
