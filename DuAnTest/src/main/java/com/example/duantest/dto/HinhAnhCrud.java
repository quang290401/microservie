package com.example.duantest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HinhAnhCrud {
    private Long id;
    private String tenAnh;
    private String duongDan;
    private LocalDateTime ngaySua;
    private LocalDateTime ngayTao;
    private Integer trangThai;
    private int sanPham;
}
