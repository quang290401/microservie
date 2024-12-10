package com.example.duantest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "HinhAnh")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_anh",columnDefinition = "NVARCHAR(255)" )
    private String tenAnh;
    @Column(name = "duong_dan",columnDefinition = "NVARCHAR(255)" )
    private String duongDan;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "trang_thai")
    private Integer trangThai;
    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPham sanPham;

    // Getters và Setters
}

