package com.example.duantest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO {
    private Long id;
    private BigDecimal giaTien;
    private LocalDateTime ngaySua;
    private LocalDateTime ngayTao;
    private String nguoiSua;
    private String nguoiTao;
    private Integer soLuong;
    private Integer trangThai;
    private KichCoDTO kichCo;
    private MauSacDTO mauSac;
    private SanPhamDTO sanPham;

}
