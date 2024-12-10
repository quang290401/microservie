package com.example.taskvietsoft.service.impl;

import com.example.taskvietsoft.dto.DanhMucDTO;
import com.example.taskvietsoft.entity.DanhMucEntity;
import com.example.taskvietsoft.repository.DanhMucRepository;
import com.example.taskvietsoft.service.DanhMucService;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
//Lấy dữ liệu từ DanhMucRepository
//Lớp này được gọi ở controller để sử dụng
//Chưc năng chính :Lấy danh sách danh_muc ,phân trang
public class DanhMucServiceImpl implements DanhMucService {
    private final DanhMucRepository danhMucRepository;
    private final ModelMapper modelMapper;

    //lấy danh sách danh mục và phân trang ;
    //@param truyền vào (Integer pageNo,Integer PageSize)
    @Override
    public Page<DanhMucDTO> getAll(Integer pageNo, Integer pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<DanhMucEntity> getAll = danhMucRepository.findAll(pageable);

            // Chuyển đổi từ entity sang DTO
            List<DanhMucDTO> dtos = getAll.getContent().stream()
                    .map(entity -> modelMapper.map(entity, DanhMucDTO.class))
                    .toList();

            return new PageImpl<>(dtos, pageable, getAll.getTotalElements());
        } catch (Exception e) {
            throw new ServiceException("Không thể truy xuất danh mục data", e); // Ném ngoại lệ
        }
    }

}
