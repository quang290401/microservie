package com.example.duantest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "phuong_thuc_thanh_toan")
@Data
public class PhuongThucThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ten",columnDefinition = "NVARCHAR(255)" )
    private String ten;

    @Column(name = "trang_thai")
    private Integer trangThai;

    // Getters và Setters
}

