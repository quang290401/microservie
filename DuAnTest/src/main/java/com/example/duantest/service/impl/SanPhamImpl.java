package com.example.duantest.service.impl;


import com.example.duantest.dto.FiterDTO.SanPhamFiterDTO;
import com.example.duantest.dto.SanPhamCRUD;
import com.example.duantest.dto.SanPhamDTO;
import com.example.duantest.entity.ChatLieu;
import com.example.duantest.entity.KieuDang;
import com.example.duantest.entity.LoaiSanPham;
import com.example.duantest.entity.SanPham;
import com.example.duantest.repository.ChatLieuRepository;
import com.example.duantest.repository.KieuDangRepository;
import com.example.duantest.repository.LoaiSanPhamRepository;
import com.example.duantest.repository.SanPhamRepository;
import com.example.duantest.service.SanPhamService;
import com.example.duantest.service.Spectification.SanPhamSpectifilecation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SanPhamImpl implements SanPhamService {
    private final ModelMapper modelMapper;
    private final SanPhamRepository sanPhamRepository;
    private final KieuDangRepository kieuDangRepository;
    private final ChatLieuRepository chatLieuRepository;
    private final LoaiSanPhamRepository loaiSanPhamRepository;

    @Override
    public Page<SanPhamDTO> getAllSanPham(Integer totalPage, Integer totalItem, SanPhamFiterDTO form) {
        Pageable pageable = PageRequest.of(totalPage, totalItem);

        // Xây dựng Specification từ form (SanPhamChiTietFiterDTO)
        Specification<SanPham> specification = SanPhamSpectifilecation.buildWhere(form);

        // Kiểm tra nếu không có Specification hoặc Specification là null
        if (specification == null) {
            List<SanPhamDTO> emptyList = Collections.emptyList();
            return new PageImpl<>(emptyList, pageable, 0);
        }

        Page<SanPham> entityPage = sanPhamRepository.findAll(specification, pageable);
        // Convert Page<SanPhamChiTietEntity> sang Page<SanPhamChiTietDTO>
        List<SanPhamDTO> dtos = entityPage.getContent().stream()
                .map(entity -> modelMapper.map(entity, SanPhamDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, entityPage.getTotalElements());
    }

    @Override
    public SanPhamCRUD addSanPham(SanPhamCRUD sanPhamCRUD) {
        try {
            Optional<KieuDang> kieuDang = kieuDangRepository.findById(sanPhamCRUD.getKieuDang());
            Optional<ChatLieu> chatLieu = chatLieuRepository.findById(sanPhamCRUD.getChatLieu());
            Optional<LoaiSanPham> loaiSanPham = loaiSanPhamRepository.findById(sanPhamCRUD.getLoaiSanPham());

            // Kiểm tra sự tồn tại của các đối tượng và ném ngoại lệ nếu không tìm thấy
            if (!kieuDang.isPresent()) {
                throw new EntityNotFoundException("Không tìm thấy kiểu dáng với ID: " + sanPhamCRUD.getKieuDang());
            }
            if (!chatLieu.isPresent()) {
                throw new EntityNotFoundException("Không tìm thấy chất liệu với ID: " + sanPhamCRUD.getChatLieu());
            }
            if (!loaiSanPham.isPresent()) {
                throw new EntityNotFoundException("Không tìm thấy loại sản phẩm với ID: " + sanPhamCRUD.getLoaiSanPham());
            }
            int index = 0; // Khởi tạo biến index
            index++; //


            SanPham sanPham = SanPham.builder()
                    .tenSanPham(sanPhamCRUD.getTenSanPham())
                    .ma("Sp00"+index)
                    .loaiSanPham(loaiSanPham.get())
                    .chatLieu(chatLieu.get())
                    .kieuDang(kieuDang.get())
                    .ngayTao(LocalDateTime.now())
                    .trangThai(sanPhamCRUD.getTrangThai())
                    .moTa(sanPhamCRUD.getMoTa())
                    .build();
            SanPham sanPhamSave = sanPhamRepository.save(sanPham);

            return modelMapper.map(sanPhamSave, SanPhamCRUD.class);
        } catch (EntityNotFoundException e) {
            // Ghi log ngoại lệ
            System.err.println("Lỗi: " + e.getMessage());

            // Trả về null hoặc một đối tượng trống nếu cần thiết
            return null; // Hoặc có thể trả về một đối tượng SanPhamCRUD với thông tin lỗi
        }
    }

    @Override
    public SanPhamCRUD updateSanPham(SanPhamCRUD sanPhamCRUD) {
        try {
            Optional<KieuDang> kieuDang = kieuDangRepository.findById(sanPhamCRUD.getKieuDang());
            Optional<ChatLieu> chatLieu = chatLieuRepository.findById(sanPhamCRUD.getChatLieu());
            Optional<LoaiSanPham> loaiSanPham = loaiSanPhamRepository.findById(sanPhamCRUD.getLoaiSanPham());

            // Kiểm tra sự tồn tại của các đối tượng và ném ngoại lệ nếu không tìm thấy
            if (!kieuDang.isPresent()) {
                throw new EntityNotFoundException("Không tìm thấy kiểu dáng với ID: " + sanPhamCRUD.getKieuDang());
            }
            if (!chatLieu.isPresent()) {
                throw new EntityNotFoundException("Không tìm thấy chất liệu với ID: " + sanPhamCRUD.getChatLieu());
            }
            if (!loaiSanPham.isPresent()) {
                throw new EntityNotFoundException("Không tìm thấy loại sản phẩm với ID: " + sanPhamCRUD.getLoaiSanPham());
            }
            int index = 0; // Khởi tạo biến index
            index++; //


            SanPham sanPham = SanPham.builder()
                    .tenSanPham(sanPhamCRUD.getTenSanPham())
                    .ma("Sp00"+index)
                    .loaiSanPham(loaiSanPham.get())
                    .chatLieu(chatLieu.get())
                    .kieuDang(kieuDang.get())
                    .ngayTao(LocalDateTime.now())
                    .trangThai(sanPhamCRUD.getTrangThai())
                    .moTa(sanPhamCRUD.getMoTa())
                    .build();
            sanPham.setId(sanPhamCRUD.getId());
            SanPham sanPhamSave = sanPhamRepository.save(sanPham);

            return modelMapper.map(sanPhamSave, SanPhamCRUD.class);
        } catch (EntityNotFoundException e) {
            // Ghi log ngoại lệ
            System.err.println("Lỗi: " + e.getMessage());

            // Trả về null hoặc một đối tượng trống nếu cần thiết
            return null; // Hoặc có thể trả về một đối tượng SanPhamCRUD với thông tin lỗi
        }
    }

}
