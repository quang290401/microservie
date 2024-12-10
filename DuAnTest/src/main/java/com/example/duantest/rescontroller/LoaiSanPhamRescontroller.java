package com.example.duantest.rescontroller;

import com.example.duantest.dto.LoaiSanPhamDTO;
import com.example.duantest.service.LoaiSanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loai-sp")
public class LoaiSanPhamRescontroller {
    private final LoaiSanPhamService loaiSanPhamService;

    @GetMapping("/getAll")
    public List<LoaiSanPhamDTO> getAllMauSac() {
        return loaiSanPhamService.getAllLoaiSanPham();
    }
    @PostMapping()
    public LoaiSanPhamDTO addLoaiSP(@RequestBody  LoaiSanPhamDTO loaiSanPhamDTO){
        return loaiSanPhamService.addLoaiSanPham(loaiSanPhamDTO);

    }
    @PutMapping()
    public LoaiSanPhamDTO uppDateLoaiSP(@RequestBody  LoaiSanPhamDTO loaiSanPhamDTO){
        return loaiSanPhamService.updateLoaiSanPham(loaiSanPhamDTO);

    }
}
