package com.example.duantest.rescontroller;


import com.example.duantest.common.Appcontants;
import com.example.duantest.dto.FiterDTO.SanPhamFiterDTO;
import com.example.duantest.dto.SanPhamCRUD;
import com.example.duantest.dto.SanPhamDTO;
import com.example.duantest.entity.SanPham;
import com.example.duantest.repository.SanPhamRepository;
import com.example.duantest.service.SanPhamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sp")
public class SanPhamRestController {
    private final SanPhamService service;
    private final SanPhamRepository sanPhamRepository;

    @GetMapping()
    public Page<SanPhamDTO> getAllProducts(
            @RequestParam(value = "pageNo", defaultValue = Appcontants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Appcontants.DEFAULT_TOTAL_NUMBER, required = false) int pageSize,
            @Valid SanPhamFiterDTO filterForm
    ) {
        return service.getAllSanPham(pageNo, pageSize,filterForm);
    }
    @GetMapping("/getAll")
    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();

    }
    @PostMapping()
    public SanPhamCRUD addSanPham(@RequestBody SanPhamCRUD sanPhamCRUD){
        return service.addSanPham(sanPhamCRUD);

    }
    @PutMapping("/update")
    public SanPhamCRUD updateSanPham(@RequestBody SanPhamCRUD sanPhamCRUD){
        return service.updateSanPham(sanPhamCRUD);

    }

}
