package com.example.taskvietsoft.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//  Data Transfer Object (DTO) đại diện cho danh_muc.
//  Được sử dụng để truyền tải dữ liệu sản phẩm giữa các tầng trong ứng dụng,
//  tránh việc truyền trực tiếp các thực thể (Entity) từ cơ sở dữ liệu.
public class DanhMucDTO extends SuperDTO{
    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(max = 100, message = "Tên danh mục không được vượt quá 100 ký tự")
    private String tenDanhMuc;
}
