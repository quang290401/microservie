package com.example.duantest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SanPhamCRUD {
    private Long id;
    private String ma;
    private String moTa;
    private LocalDateTime ngaySua;
    private LocalDateTime ngayTao;
    private String tenSanPham;
    private Integer trangThai;
    private int chatLieu;
    private int loaiSanPham;
    private int kieuDang;
}
