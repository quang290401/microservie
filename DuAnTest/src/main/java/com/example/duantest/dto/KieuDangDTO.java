package com.example.duantest.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class KieuDangDTO {

    private Long id;


    private LocalDateTime ngaySua;


    private LocalDateTime ngayTao;


    private String ten;


    private Integer trangThai;
}
