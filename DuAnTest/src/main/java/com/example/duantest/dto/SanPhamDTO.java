package com.example.duantest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class  SanPhamDTO {

    private Long id;
    private String ma;
    private String moTa;
    private LocalDateTime ngaySua;
    private LocalDateTime ngayTao;
    private String tenSanPham;
    private Integer trangThai;
    private ChatLieuDTO chatLieu;
    private LoaiSanPhamDTO loaiSanPham;
    private KieuDangDTO kieuDang;
}
