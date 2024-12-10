package com.example.taskvietsoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "ThuongHieu") // tên bảng
// Đại diện cho bảng thuong_hieu trong cơ sở dữ liệu
//tương tác với cơ sở dữ liệu, giúp thao tác dữ liệu dễ dàng hơn.
public class ThuongHieuEntity  extends SuperEntity{
    //tên bảng , số lượng kí tự cho phép , NotNull
    @Column(name = "tenThuongHieu", length = 150, nullable = false)
    private String tenThuongHieu;

    @JsonIgnore
    @OneToMany(mappedBy = "thuongHieu")
    private List<SanPhamEntity> sanPhamChiTiets = new ArrayList<>();
}
