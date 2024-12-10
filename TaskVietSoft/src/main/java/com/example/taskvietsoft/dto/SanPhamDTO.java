package com.example.taskvietsoft.dto;

import com.example.taskvietsoft.common.TrangThai;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

//  Data Transfer Object (DTO) đại diện cho sản phẩm.
//  Được sử dụng để truyền tải dữ liệu sản phẩm giữa các tầng trong ứng dụng,
//  tránh việc truyền trực tiếp các thực thể (Entity) từ cơ sở dữ liệu.
public class SanPhamDTO {

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 100, message = "Tên sản phẩm không được vượt quá 100 ký tự")
    private String tenSanPham;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá sản phẩm phải lớn hơn 0")
    private BigDecimal giaSanPham;

    @Min(value = 1, message = "Số lượng sản phẩm phải ít nhất là 1")
    private int soLuong;

    @NotBlank(message = "Trọng lượng không được để trống")
    private String trongLuong;

    @Size(max = 1000, message = "Mô tả sản phẩm không được vượt quá 1000 ký tự")
    private String moTa;

    @NotNull(message = "Trạng thái không được để trống")
    private TrangThai trangThai;

    @NotNull(message = "Danh mục không được để trống")
    @Valid
    private DanhMucDTO danhMuc;

    @NotNull(message = "Thương hiệu không được để trống")
    @Valid
    private ThuongHieuDTO thuongHieu;

    // Getters và Setters
}

