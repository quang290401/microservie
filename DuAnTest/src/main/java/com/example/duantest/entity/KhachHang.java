package com.example.duantest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "khach_hang")
@Data
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "gioi_tinh")
    private Integer gioiTinh;

    @Column(name = "ho")
    private String ho;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "sdt", length = 15)
    private String sdt;

    @Column(name = "tai_khoan")
    private String taiKhoan;

    @Column(name = "ten",columnDefinition = "NVARCHAR(255)" )
    private String ten;

    @Column(name = "ten_dem",columnDefinition = "NVARCHAR(255)" )
    private String tenDem;

    @Column(name = "trang_thai")
    private Integer trangThai;

    // Khóa ngoại đến DiaChi
    @ManyToOne
    @JoinColumn(name = "dia_chi_id")
    private DiaChi diaChi;

    @Column(name = "vai_tro", nullable = false)
    private Integer vaiTro;

    // Getters và Setters
}

