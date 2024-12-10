package com.example.duantest.service.impl;

import com.example.duantest.dto.MauSacDTO;
import com.example.duantest.entity.MauSac;
import com.example.duantest.repository.MauSacRepository;
import com.example.duantest.service.MauSacService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MauSacImpl implements MauSacService {
    private final MauSacRepository mauSacRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<MauSacDTO> getAllMauSac() {
        List<MauSac> mauSacs = mauSacRepository.findAll();
        return mauSacs.stream()
                .map(entity -> modelMapper.map(entity, MauSacDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MauSacDTO addMauSac̣̣(MauSacDTO mauSacDTO) {
        Random random = new Random();
        int randomInt = random.nextInt(10);
        MauSac mauSac = MauSac.builder()
                .ma("MS00"+randomInt)
                .ten(mauSacDTO.getTen())
                .ngayTao(LocalDateTime.now())
                .trangThai(mauSacDTO.getTrangThai())
                .build();
         MauSac mauSac1 = mauSacRepository.save(mauSac);
        return modelMapper.map(mauSac1,MauSacDTO.class);
    }

    @Override
    public MauSacDTO upDateMauSac(MauSacDTO mauSacDTO) {
        MauSac mauSac = MauSac.builder()
                .ma(mauSacDTO.getMa())
                .ten(mauSacDTO.getTen())
                .ngayTao(LocalDateTime.now())
                .trangThai(mauSacDTO.getTrangThai())
                .build();
        mauSac.setId(mauSacDTO.getId());
        MauSac mauSac1 = mauSacRepository.save(mauSac);
        return modelMapper.map(mauSac1,MauSacDTO.class);
    }
}
