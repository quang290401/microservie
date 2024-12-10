package com.example.taskvietsoft.entity;

import com.example.taskvietsoft.common.TrangThai;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "SanPham") // tên bảng
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Đại diện cho bảng san_pham trong cơ sở dữ liệu
//tương tác với cơ sở dữ liệu, giúp thao tác dữ liệu dễ dàng hơn.
public class SanPhamEntity extends SuperEntity {
    // tên bảng , số lượng kí tự cho phéo , NotNull
    @Column(name = "tenSanPham", length = 150, nullable = false)
    private String tenSanPham;

    @Column(name = "giaSanPham", nullable = false)
    @Digits(integer = 13, fraction = 2) // 13 chữ số ở phần nguyên, 2 chữ số ở phần thập phân
    private BigDecimal giaSanPham;

    @Column(name = "soLuong", nullable = false)
    @Min(0) // giá trị tối thiểu là 0
    private int soLuong;

    @Column(name = "trongLuong", length = 50, nullable = false )
    private String trongLuong;
    @Lob // lưu dữ liệu kiểu text
    @Column(name = "moTa")
    private String moTa;
    @Column(name = "trangThai", length = 1, nullable = false )
    @Enumerated(EnumType.STRING) // Kiểu dư liệu enum để lưu trạng thái
    private TrangThai trangThai;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "danhMuc_id") // nắm khóa ngoại của bảng Danh_muc
    private DanhMucEntity danhMuc;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "thuongHieu_id") /// nắm khóa ngoại của bảng thuong_hieu
    private ThuongHieuEntity thuongHieu;


}
