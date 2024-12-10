package com.example.duantest.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "san_pham")
@Builder
@Getter
@Setter
public class SanPham {

    public SanPham() {
    }

    public SanPham(Long id, String ma, String moTa, LocalDateTime ngaySua, LocalDateTime ngayTao, String tenSanPham, Integer trangThai, ChatLieu chatLieu, LoaiSanPham loaiSanPham, KieuDang kieuDang) {
        this.id = id;
        this.ma = ma;
        this.moTa = moTa;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
        this.tenSanPham = tenSanPham;
        this.trangThai = trangThai;
        this.chatLieu = chatLieu;
        this.loaiSanPham = loaiSanPham;
        this.kieuDang = kieuDang;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "mo_ta",columnDefinition = "NVARCHAR(255)" )
    private String moTa;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ten_san_pham",columnDefinition = "NVARCHAR(255)" )
    private String tenSanPham;

    @Column(name = "trang_thai")
    private Integer trangThai;

    // Khóa ngoại đến ChatLieu
    @ManyToOne
    @JoinColumn(name = "chat_lieu_id")
    private ChatLieu chatLieu;

    // Khóa ngoại đến LoaiSP
    @ManyToOne
    @JoinColumn(name = "loai_sp_id")
    private LoaiSanPham loaiSanPham;

    // Khóa ngoại đến KieuDang
    @ManyToOne
    @JoinColumn(name = "kieu_dang_id")
    private KieuDang kieuDang;



    // Getters và Setters
}
