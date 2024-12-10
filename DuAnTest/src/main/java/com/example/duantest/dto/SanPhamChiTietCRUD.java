package com.example.duantest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietCRUD {
    private Long id;
    private BigDecimal giaTien;
    private LocalDateTime ngaySua;
    private LocalDateTime ngayTao;
    private String nguoiSua;
    private String nguoiTao;
    private Integer soLuong;
    private Integer trangThai;
    private Long kichCo;
    private Long mauSac;
    private Long sanPham;
}
