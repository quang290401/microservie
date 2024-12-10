package com.example.duantest.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = "gio_hang_chi_tiet")
@Data
public class GioHangChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ghi_chu",columnDefinition = "NVARCHAR(255)" )
    private String ghiChu;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "gio_hang_id")
    private GioHang gioHang; //


}
