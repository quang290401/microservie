package com.example.duantest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "hoa_don_chi_tiet")
@Data
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "don_gia", precision = 38, scale = 2)
    private BigDecimal donGia;

    @Column(name = "ghi_chu",columnDefinition = "NVARCHAR(255)" )
    private String ghiChu;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "nguoi_sua",columnDefinition = "NVARCHAR(255)" )
    private String nguoiSua;

    @Column(name = "nguoi_tao",columnDefinition = "NVARCHAR(255)" )
    private String nguoiTao;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "trang_thai")
    private Integer trangThai;

    // Khóa ngoại đến ChiTietSanPham
    @ManyToOne
    @JoinColumn(name = "chi_tiet_san_pham_id")
    private ChiTietSanPham chiTietSanPham;

    // Khóa ngoại đến HoaDon
    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    private HoaDon hoaDon;

    // Getters và Setters
}

