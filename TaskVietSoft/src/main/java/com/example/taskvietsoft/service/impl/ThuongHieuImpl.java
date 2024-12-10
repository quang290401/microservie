package com.example.taskvietsoft.service.impl;
import com.example.taskvietsoft.dto.ThuongHieuDTO;
import com.example.taskvietsoft.entity.ThuongHieuEntity;
import com.example.taskvietsoft.repository.ThuongHieuRepository;
import com.example.taskvietsoft.service.ThuongHieuService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@RequiredArgsConstructor
@Service
//Lấy dữ liệu từ sanPhamRepository
//Lớp này được gọi ở controller để sử dụng
// Lấy danh sách thuong_hieu, phân trang
public class ThuongHieuImpl implements ThuongHieuService {
    private final ThuongHieuRepository thuongHieuRepository;
    private final ModelMapper modelMapper;
    // lấy danh sách thương hiệu và phân trang ;
    // Dữ liệu truyền vào (Integer pageNo,Integer PageSize)
    @Override
    public Page<ThuongHieuDTO> getAll(Integer pageNo, Integer pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<ThuongHieuEntity> getAll = thuongHieuRepository.findAll(pageable);
            // Chuyển đổi từ entity sang DTO
            List<ThuongHieuDTO> dtos = getAll.getContent().stream()
                    .map(entity -> modelMapper.map(entity, ThuongHieuDTO.class))
                    .toList();

            return new PageImpl<>(dtos, pageable, getAll.getTotalElements());
        } catch (Exception e) {

            throw new ServiceException("Không thể truy xuất thương hiệu data", e); // Ném ngoại lệ
        }
    }
}
