package com.example.taskvietsoft.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
//  Data Transfer Object (DTO) đại diện cho hinh_anh.
//  Được sử dụng để truyền tải dữ liệu sản phẩm giữa các tầng trong ứng dụng,
//  tránh việc truyền trực tiếp các thực thể (Entity) từ cơ sở dữ liệu.
public class HinhAnhDTO extends SuperDTO{
    @NotBlank(message = "file anh sản phẩm không được để trống")
    private String filePath;
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private SanPhamDTO sanPham;
}
