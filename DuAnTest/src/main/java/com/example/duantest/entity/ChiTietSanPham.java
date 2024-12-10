package com.example.duantest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "chi_tiet_san_pham")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gia_tien", precision = 38, scale = 2)
    private BigDecimal giaTien;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "nguoi_sua")
    private String nguoiSua;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

    // Khóa ngoại đến KichCo
    @ManyToOne
    @JoinColumn(name = "kich_co_id")
    private KichCo kichCo;

    @ManyToOne
    @JoinColumn(name = "mau_sac_id")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPham sanPham;

    // Getters và Setters
}
