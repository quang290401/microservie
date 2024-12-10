package com.example.duantest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "kieu_dang")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KieuDang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
