package com.example.duantest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MauSacDTO {

    private Long id;


    private String ma;


    private LocalDateTime ngaySua;


    private LocalDateTime ngayTao;


    private String ten;


    private Integer trangThai;
}
