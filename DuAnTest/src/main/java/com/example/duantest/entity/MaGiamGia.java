package com.example.duantest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ma_giam_gia")
@Data
public class MaGiamGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "don_toi_thieu", precision = 38, scale = 2)
    private BigDecimal donToiThieu;

    @Column(name = "gia_tri_giam", precision = 38, scale = 2)
    private BigDecimal giaTriGiam;

    @Column(name = "giam_toi_da", precision = 38, scale = 2)
    private BigDecimal giamToiDa;

    @Column(name = "loai_voucher")
    private Integer loaiVoucher;

    @Column(name = "ma")
    private String ma;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "so_lan_su_dung")
    private Integer soLanSuDung;

    @Column(name = "ngay_bat_dau")
    private LocalDateTime ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDateTime ngayKetThuc;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ten")
    private String ten;

    @Column(name = "trang_thai")
    private Integer trangThai;

    // Getters và Setters
}
