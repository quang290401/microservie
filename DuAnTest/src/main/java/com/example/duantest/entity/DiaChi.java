package com.example.duantest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "dia_chi")
@Data
public class DiaChi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dia_chi",columnDefinition = "NVARCHAR(255)" )
    private String diaChi;

    @Column(name = "dia_chi_cu_the",columnDefinition = "NVARCHAR(255)" )
    private String diaChiCuThe;

    @Column(name = "ho_va_ten",columnDefinition = "NVARCHAR(255)" )
    private String hoVaTen;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "phuong_xa",columnDefinition = "NVARCHAR(255)" )
    private String phuongXa;

    @Column(name = "quan_huyen",columnDefinition = "NVARCHAR(255)" )
    private String quanHuyen;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "thanh_pho",columnDefinition = "NVARCHAR(255)" )
    private String thanhPho;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

    // Getters và Setters
}
