package com.example.duantest.service.impl;


import com.example.duantest.dto.FiterDTO.SanPhamFiterDTO;
import com.example.duantest.dto.SanPhamChiTietCRUD;
import com.example.duantest.dto.SanPhamChiTietDTO;
import com.example.duantest.entity.ChiTietSanPham;
import com.example.duantest.entity.KichCo;
import com.example.duantest.entity.MauSac;
import com.example.duantest.entity.SanPham;
import com.example.duantest.repository.KichCoRepository;
import com.example.duantest.repository.MauSacRepository;
import com.example.duantest.repository.SanPhamChiTietRepository;
import com.example.duantest.repository.SanPhamRepository;
import com.example.duantest.service.SanPhamChiTietService;
import com.example.duantest.service.Spectification.SpectificationSpct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SanPhamChiTietImpl implements SanPhamChiTietService {
    private final SanPhamChiTietRepository sanPhamChiTietRepository;
    private final KichCoRepository kichCoRepository;
    private final MauSacRepository sacRepository;
    private final SanPhamRepository sanPhamRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<SanPhamChiTietDTO> getAllSanPhamChiTietBYidSP(Long idSP, Integer totalPage, Integer totalItem, SanPhamFiterDTO fiterDTO) {
        // Tạo Specification để lọc theo idSP
        Specification<ChiTietSanPham> specByIdSP = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("sanPham").get("id"), idSP);


        Specification<ChiTietSanPham> specByFilter = SpectificationSpct.buildWhereCT(fiterDTO);
        // Kết hợp các Specifications
        Specification<ChiTietSanPham> combinedSpec = Specification.where(specByIdSP).and(specByFilter);

        Pageable pageable = PageRequest.of(totalPage, totalItem);

        // Truy vấn với combined Specification và Pageable
        Page<ChiTietSanPham> entityPage = sanPhamChiTietRepository.findAll(combinedSpec, pageable);

        // Chuyển đổi từ entity sang DTO
        List<SanPhamChiTietDTO> dtos = entityPage.getContent().stream()
                .map(entity -> modelMapper.map(entity, SanPhamChiTietDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, entityPage.getTotalElements());
    }

    @Override
    public SanPhamChiTietCRUD addSanPham(SanPhamChiTietCRUD sanPhamChiTietCRUD) {
        Optional<KichCo> kichCo = kichCoRepository.findById(sanPhamChiTietCRUD.getKichCo());
        Optional<MauSac> mauSac = sacRepository.findById(sanPhamChiTietCRUD.getMauSac());
        Optional<SanPham> sanPham = sanPhamRepository.findById(Math.toIntExact(sanPhamChiTietCRUD.getSanPham()));
        ChiTietSanPham chiTietSanPham = ChiTietSanPham.builder()
                .giaTien(sanPhamChiTietCRUD.getGiaTien())
                .ngayTao(LocalDateTime.now())
                .nguoiTao("Admin")
                .soLuong(sanPhamChiTietCRUD.getSoLuong())
                .sanPham(sanPham.get())
                .trangThai(sanPhamChiTietCRUD.getTrangThai())
                .kichCo(kichCo.get())
                .mauSac(mauSac.get())
                .build();
        ChiTietSanPham chiTietSanPham1 = sanPhamChiTietRepository.save(chiTietSanPham);
        return modelMapper.map(chiTietSanPham1, SanPhamChiTietCRUD.class);
    }

    @Override
    public SanPhamChiTietCRUD updateSanPham(SanPhamChiTietCRUD sanPhamChiTietCRUD) {
        Optional<KichCo> kichCo = kichCoRepository.findById(sanPhamChiTietCRUD.getKichCo());
        Optional<MauSac> mauSac = sacRepository.findById(sanPhamChiTietCRUD.getMauSac());
        Optional<SanPham> sanPham = sanPhamRepository.findById(Math.toIntExact(sanPhamChiTietCRUD.getSanPham()));
        ChiTietSanPham chiTietSanPham = ChiTietSanPham.builder()
                .giaTien(sanPhamChiTietCRUD.getGiaTien())
                .ngaySua(LocalDateTime.now())
                .nguoiTao("Admin")
                .soLuong(sanPhamChiTietCRUD.getSoLuong())
                .sanPham(sanPham.get())
                .trangThai(sanPhamChiTietCRUD.getTrangThai())
                .kichCo(kichCo.get())
                .mauSac(mauSac.get())
                .build();
        chiTietSanPham.setId(sanPhamChiTietCRUD.getId());

        ChiTietSanPham chiTietSanPham1 = sanPhamChiTietRepository.save(chiTietSanPham);
        return modelMapper.map(chiTietSanPham1, SanPhamChiTietCRUD.class);
    }
}
