package com.example.duantest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_lieu")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatLieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten" ,columnDefinition = "NVARCHAR(255)")
    private String ten;

    @Column(name = "mo_ta",columnDefinition = "NVARCHAR(255)" )
    private String moTa;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "trang_thai")
    private Integer trangThai;

    // Getters và Setters
}

