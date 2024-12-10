package com.example.taskvietsoft.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "HinhAnh")// tên bảng
//Đại diện cho bảng hinh_anh trong cơ sở dữ liệu
//tương tác với cơ sở dữ liệu, giúp thao tác dữ liệu dễ dàng hơn.
public class HinhAnhEntity extends SuperEntity {
    // tên bảng , số lượng kí tự cho phép , NotNull
    @Column(name = "filePath", length = 100, nullable = false )
    private String filePath;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sanpham_id")
    private SanPhamEntity sanPham;
}
