package com.example.taskvietsoft.service.impl;

import com.example.taskvietsoft.dto.SanPhamDTO;
import com.example.taskvietsoft.entity.SanPhamEntity;
import com.example.taskvietsoft.repository.SanPhamRepository;
import com.example.taskvietsoft.service.SanPhamService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//Lấy dữ liệu từ sanPhamRepository
//Lớp này được gọi ở controller để sử dụng
//Chưc năng chính :Lấy  sản phẩm  theo danh mục ID,
// Lấy danh sách san_pham ID, phân trang
public class SanPhamImpl implements SanPhamService {
    private final SanPhamRepository sanPhamRepository;
    private final ModelMapper modelMapper;

    @Override
    //    lấy sản phẩm dựa vào id;
    //    @param Long(id)
    public SanPhamDTO findById(Long id) {
        Optional<SanPhamEntity> sanPham = sanPhamRepository.findById(id);
        if (sanPham.isPresent()) {
            return modelMapper.map(sanPham.get(), SanPhamDTO.class);
        } else {
            // Xử lý khi không tìm thấy sản phẩm
            throw new EntityNotFoundException(" Sản phẩm không tồn tại với ID: " + id);

        }
    }
    @Transactional
    //    lấy sản phẩm dựa vào id;
    //    @param pageNo Số trang cần lấy dữ liệu. (Trang bắt đầu từ 0)
    //    @param pageSize Số lượng sản phẩm trong mỗi trang.
    public List<SanPhamDTO> getSanPhamChiTietByDanhMuc(Long id,Integer pageNo,Integer pageSize) {
        Pageable pageable =PageRequest.of(pageNo,pageSize);
            // Lấy danh sách sản phẩm chi tiết từ repository
            List<SanPhamEntity> getAllByIdDanhMuc = sanPhamRepository.getSanPhamChiTietByDanhMuc(id,pageable);
            if (getAllByIdDanhMuc.isEmpty()) {
                throw new ServiceException("Không tìm thấy sản phẩm cho danh mục: " + id);
            }
            // Chuyển đổi thành DTOs và trả về danh sách
            return getAllByIdDanhMuc.stream()
                    .map(entity -> modelMapper.map(entity, SanPhamDTO.class))
                    .collect(Collectors.toList());
    }



}
