package com.example.duantest.service.impl;

import com.example.duantest.dto.KieuDangDTO;
import com.example.duantest.entity.KieuDang;
import com.example.duantest.repository.KieuDangRepository;
import com.example.duantest.service.KieuDangService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KieuDangImpl implements KieuDangService {
    private final KieuDangRepository kieuDangRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<KieuDangDTO> getAllKieuDang() {
        List<KieuDang> kieuDangs = kieuDangRepository.findAll();
        return kieuDangs.stream()
                .map(entity -> modelMapper.map(entity, KieuDangDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public KieuDangDTO addKieuDang(KieuDangDTO kieuDangDTO) {
        KieuDang kieuDang = KieuDang.builder()
                .ten(kieuDangDTO.getTen())
                .trangThai(kieuDangDTO.getTrangThai())
                .ngayTao(LocalDateTime.now())
                .build();
        KieuDang kieuDang1 = kieuDangRepository.save(kieuDang);
        return modelMapper.map(kieuDang1, KieuDangDTO.class);
    }

    @Override
    public KieuDangDTO updateKieuDang(KieuDangDTO kieuDangDTO) {
        KieuDang kieuDang = KieuDang.builder()
                .ten(kieuDangDTO.getTen())
                .trangThai(kieuDangDTO.getTrangThai())
                .ngayTao(LocalDateTime.now())
                .build();
        kieuDang.setId(kieuDangDTO.getId());
        KieuDang kieuDang1 = kieuDangRepository.save(kieuDang);
        return modelMapper.map(kieuDang1, KieuDangDTO.class);
    }
}
