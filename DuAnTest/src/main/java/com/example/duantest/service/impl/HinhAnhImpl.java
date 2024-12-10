package com.example.duantest.service.impl;

import com.example.duantest.dto.HinhAnhCrud;
import com.example.duantest.dto.HinhAnhDTO;
import com.example.duantest.entity.*;
import com.example.duantest.repository.HinhAnhRepository;
import com.example.duantest.repository.SanPhamRepository;
import com.example.duantest.service.HinhAnhService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HinhAnhImpl implements HinhAnhService {
    private final ModelMapper modelMapper;
    private final SanPhamRepository sanPhamRepository;
    private final HinhAnhRepository hinhAnhRepository;
    @Override
    public Page<HinhAnhDTO> getAllHinhAnh(Integer totalPage, Integer totalItem) {
        Pageable pageable = PageRequest.of(totalPage, totalItem);

        Page<HinhAnh> entityPage = hinhAnhRepository.findAll(pageable);

        List<HinhAnhDTO> dtos = entityPage.getContent().stream()
                .map(entity -> modelMapper.map(entity, HinhAnhDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, entityPage.getTotalElements());
    }

    @Override
    public HinhAnhCrud addHinhAnh(HinhAnhCrud hinhAnhCrud) {
        try {
            Optional<SanPham> sanPham = sanPhamRepository.findById(hinhAnhCrud.getSanPham());


            // Kiểm tra sự tồn tại của các đối tượng và ném ngoại lệ nếu không tìm thấy
            if (!sanPham.isPresent()) {
                throw new EntityNotFoundException("Không tìm thấy san Phâm với ID: " + hinhAnhCrud.getSanPham());
            }

            HinhAnh hinhAnh = HinhAnh.builder()
                    .tenAnh(hinhAnhCrud.getTenAnh())
                    .duongDan(hinhAnhCrud.getDuongDan())
                    .sanPham(sanPham.get())
                    .trangThai(hinhAnhCrud.getTrangThai())
                    .ngayTao(LocalDateTime.now())
                    .build();
            HinhAnh hinhAnhSave = hinhAnhRepository.save(hinhAnh);

            return modelMapper.map(hinhAnhSave, HinhAnhCrud.class);
        } catch (EntityNotFoundException e) {
            // Ghi log ngoại lệ
            System.err.println("Lỗi: " + e.getMessage());

            // Trả về null hoặc một đối tượng trống nếu cần thiết
            return null; // Hoặc có thể trả về một đối tượng SanPhamCRUD với thông tin lỗi
        }
    }

    @Override
    public HinhAnhCrud updateHinhAnh(HinhAnhCrud hinhAnhCrud) {
        try {
            Optional<SanPham> sanPham = sanPhamRepository.findById(hinhAnhCrud.getSanPham());


            // Kiểm tra sự tồn tại của các đối tượng và ném ngoại lệ nếu không tìm thấy
            if (!sanPham.isPresent()) {
                throw new EntityNotFoundException("Không tìm thấy san Phâm với ID: " + hinhAnhCrud.getSanPham());
            }

            HinhAnh hinhAnh = HinhAnh.builder()
                    .id(hinhAnhCrud.getId())
                    .tenAnh(hinhAnhCrud.getTenAnh())
                    .duongDan(hinhAnhCrud.getDuongDan())
                    .sanPham(sanPham.get())
                    .trangThai(hinhAnhCrud.getTrangThai())
                    .ngayTao(LocalDateTime.now())
                    .build();
            HinhAnh hinhAnhSave = hinhAnhRepository.save(hinhAnh);

            return modelMapper.map(hinhAnhSave, HinhAnhCrud.class);
        } catch (EntityNotFoundException e) {
            // Ghi log ngoại lệ
            System.err.println("Lỗi: " + e.getMessage());

            // Trả về null hoặc một đối tượng trống nếu cần thiết
            return null; // Hoặc có thể trả về một đối tượng SanPhamCRUD với thông tin lỗi
        }
    }
}
