package com.example.duantest.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ChatLieuDTO {

    private Long id;


    private String ten;


    private String moTa;


    private LocalDateTime ngaySua;


    private LocalDateTime ngayTao;


    private Integer trangThai;
}
