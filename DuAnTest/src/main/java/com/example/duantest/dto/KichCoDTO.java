package com.example.duantest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KichCoDTO {

    private Long id;


    private Float kichCo;


    private LocalDateTime ngaySua;


    private LocalDateTime ngayTao;


    private Integer trangThai;
}
