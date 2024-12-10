package com.example.duantest.service.impl;

import com.example.duantest.dto.LoaiSanPhamDTO;
import com.example.duantest.entity.LoaiSanPham;
import com.example.duantest.repository.LoaiSanPhamRepository;
import com.example.duantest.service.LoaiSanPhamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoaiSanPhamImpl implements LoaiSanPhamService {
    private final LoaiSanPhamRepository loaiSanPhamRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<LoaiSanPhamDTO> getAllLoaiSanPham() {

        List<LoaiSanPham> loaiSanPhams = loaiSanPhamRepository.findAll();
        return loaiSanPhams.stream()
                .map(entity -> modelMapper.map(entity, LoaiSanPhamDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LoaiSanPhamDTO addLoaiSanPham(LoaiSanPhamDTO loaiSanPhamDTO) {
        LoaiSanPham loaiSanPham = LoaiSanPham.builder()
                .ten(loaiSanPhamDTO.getTen())
                .moTa(loaiSanPhamDTO.getMoTa())
                .trangThai(loaiSanPhamDTO.getTrangThai())
                .ngayTao(LocalDateTime.now())
                .build();
        LoaiSanPham loaiSanPham1 = loaiSanPhamRepository.save(loaiSanPham);
        return modelMapper.map(loaiSanPham1, LoaiSanPhamDTO.class);
    }

    @Override
    public LoaiSanPhamDTO updateLoaiSanPham(LoaiSanPhamDTO loaiSanPhamDTO) {
        LoaiSanPham loaiSanPham = LoaiSanPham.builder()
                .ten(loaiSanPhamDTO.getTen())
                .moTa(loaiSanPhamDTO.getMoTa())
                .trangThai(loaiSanPhamDTO.getTrangThai())
                .ngayTao(LocalDateTime.now())
                .build();
        loaiSanPham.setId(loaiSanPhamDTO.getId());
        LoaiSanPham loaiSanPham1 = loaiSanPhamRepository.save(loaiSanPham);
        return modelMapper.map(loaiSanPham1, LoaiSanPhamDTO.class);
    }
}
