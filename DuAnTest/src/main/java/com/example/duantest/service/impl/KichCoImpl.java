package com.example.duantest.service.impl;

import com.example.duantest.dto.KichCoDTO;
import com.example.duantest.entity.KichCo;
import com.example.duantest.repository.KichCoRepository;
import com.example.duantest.service.KichCoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KichCoImpl implements KichCoService {
    private final KichCoRepository kichCoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<KichCoDTO> getAllKichCo() {
        List<KichCo> kichCos = kichCoRepository.findAll();
        return kichCos.stream()
                .map(entity -> modelMapper.map(entity, KichCoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public KichCoDTO addKichCo(KichCoDTO kichCoDTO) {
        KichCo kichCo = KichCo.builder()
                .kichCo(kichCoDTO.getKichCo())
                .trangThai(kichCoDTO.getTrangThai())
                .ngayTao(LocalDateTime.now())
                .build();
        KichCo kichCo1 = kichCoRepository.save(kichCo);
        return modelMapper.map(kichCo1, KichCoDTO.class);
    }

    @Override
    public KichCoDTO updateKichCo(KichCoDTO kichCoDTO) {
        KichCo kichCo = KichCo.builder()
                .kichCo(kichCoDTO.getKichCo())
                .trangThai(kichCoDTO.getTrangThai())
                .ngayTao(LocalDateTime.now())
                .build();
        kichCo.setId(kichCoDTO.getId());
        KichCo kichCo1 = kichCoRepository.save(kichCo);
        return modelMapper.map(kichCo1, KichCoDTO.class);
    }

}
