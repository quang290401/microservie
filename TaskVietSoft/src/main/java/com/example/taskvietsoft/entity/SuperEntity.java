package com.example.taskvietsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
// đươc sử dụng chung để lưu trữ id ,ngày sửa ,ngày tạo
public class  SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng
    @Column(name = "id")
    private Long id; // Sử dụng Long thay vì int (Khóa chính của bảng)
    @DateTimeFormat(pattern = "dd/MM/yyyy") //định dạng theo ngày việt nam
    @Column(name = "createDate")
    private LocalDate createDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "updateDate")
    private LocalDate updateDate;
}
