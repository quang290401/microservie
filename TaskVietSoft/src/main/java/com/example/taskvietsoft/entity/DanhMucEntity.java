package com.example.taskvietsoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "DanhMuc")// tên bảng
@AllArgsConstructor
@NoArgsConstructor
//Đại diện cho bảng danh_muc trong cơ sở dữ liệu
//tương tác với cơ sở dữ liệu, giúp thao tác dữ liệu dễ dàng hơn.
public class DanhMucEntity extends SuperEntity {
    // tên bảng , số lượng kí tự cho phép , NotNull
    @Column(name = "tenDanhMuc", length = 150, nullable = false)
    private String tenDanhMuc;

    @JsonIgnore
    @OneToMany(mappedBy = "danhMuc")
    private List<SanPhamEntity> sanPhamChiTiets = new ArrayList<>();

}
