package com.example.taskvietsoft.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//  Data Transfer Object (DTO) đại diện cho thuong_hieu.
//  Được sử dụng để truyền tải dữ liệu sản phẩm giữa các tầng trong ứng dụng,
//  tránh việc truyền trực tiếp các thực thể (Entity) từ cơ sở dữ liệu.
public class ThuongHieuDTO extends SuperDTO {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 100, message = "Tên sản phẩm không được vượt quá 100 ký tự")
    private String tenThuongHieu;
}
