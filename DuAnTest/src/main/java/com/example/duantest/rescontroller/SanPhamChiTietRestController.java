package com.example.duantest.rescontroller;

import com.example.duantest.common.Appcontants;
import com.example.duantest.dto.FiterDTO.SanPhamFiterDTO;
import com.example.duantest.dto.SanPhamChiTietCRUD;
import com.example.duantest.dto.SanPhamChiTietDTO;
import com.example.duantest.service.SanPhamChiTietService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/SPCT")
public class SanPhamChiTietRestController {
    private final SanPhamChiTietService sanPhamChiTietService;
    @GetMapping("/idSP")
    public Page<SanPhamChiTietDTO> getAllProductsByidSP(
            @RequestParam(value = "pageNo", defaultValue = Appcontants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Appcontants.DEFAULT_TOTAL_NUMBER, required = false) int pageSize,
            @Valid Long idSP,
            @Valid SanPhamFiterDTO filterForm) {

        return sanPhamChiTietService.getAllSanPhamChiTietBYidSP(idSP, pageNo, pageSize, filterForm);
    }
    @PostMapping()
    public SanPhamChiTietCRUD addSanPham(@RequestBody SanPhamChiTietCRUD sanPhamChiTietCRUD){
        return sanPhamChiTietService.addSanPham(sanPhamChiTietCRUD);

    }
    @PutMapping("/update")
    public SanPhamChiTietCRUD updateSanPham(@RequestBody SanPhamChiTietCRUD sanPhamChiTietCRUD){
        return sanPhamChiTietService.updateSanPham(sanPhamChiTietCRUD);

    }
}

