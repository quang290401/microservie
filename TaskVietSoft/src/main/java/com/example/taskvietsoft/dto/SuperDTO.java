package com.example.taskvietsoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
// đươc sử dụng chung để lưu trữ id ,ngày sửa ,ngày tạo
public class SuperDTO {
    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy") //định dạng theo ngày việt nam
    private LocalDate createDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate modifyDate;
}