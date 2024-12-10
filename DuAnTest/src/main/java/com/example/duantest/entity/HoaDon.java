package com.example.duantest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "hoa_don")
@Data
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dia_chi_nguoi_nhan",columnDefinition = "NVARCHAR(255)" )
    private String diaChiNguoiNhan;

    @Column(name = "email_nguoi_nhan",columnDefinition = "NVARCHAR(255)" )
    private String emailNguoiNhan;

    @Column(name = "ghi_chu",columnDefinition = "NVARCHAR(255)" )
    private String ghiChu;

    @Column(name = "giam_gia", precision = 38, scale = 2)
    private BigDecimal giamGia;

    @Column(name = "loai_hoa_don")
    private Integer loaiHoaDon;

    @Column(name = "ma_hoa_don")
    private String maHoaDon;

    @Column(name = "ngay_nhan")
    private LocalDate ngayNhan;

    @Column(name = "ngay_nhan_du_kien")
    private LocalDate ngayNhanDuKien;

    @Column(name = "ngay_ship")
    private LocalDate ngayShip;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ngay_thanh_toan")
    private LocalDate ngayThanhToan;

    @Column(name = "nguoi_nhan")
    private String nguoiNhan;

    @Column(name = "nguoi_sua")
    private String nguoiSua;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "phi_ship", precision = 38, scale = 2)
    private BigDecimal phiShip;

    @Column(name = "sdt_nguoi_nhan")
    private String sdtNguoiNhan;

    @Column(name = "tong_tien", precision = 38, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "tong_tien_khi_giam", precision = 38, scale = 2)
    private BigDecimal tongTienKhiGiam;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

    // Khóa ngoại đến NhanVien
    @ManyToOne
    @JoinColumn(name = "nhan_vien_id")
    private NhanVien nhanVien;

    // Khóa ngoại đến KhachHang
    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    private KhachHang khachHang;

    // Khóa ngoại đến MaGiamGia
    @ManyToOne
    @JoinColumn(name = "ma_giam_gia_id")
    private MaGiamGia maGiamGia;

    // Khóa ngoại đến DotGiamGia
    @ManyToOne
    @JoinColumn(name = "dot_giam_gia_id")
    private DotGiamGia dotGiamGia;

    // Getters và Setters
}

