package com.example.duantest.service;


import com.example.duantest.dto.HinhAnhCrud;
import com.example.duantest.dto.HinhAnhDTO;
import org.springframework.data.domain.Page;

public interface HinhAnhService {
    Page<HinhAnhDTO> getAllHinhAnh(Integer totalPage, Integer totalItem);
    HinhAnhCrud addHinhAnh(HinhAnhCrud hinhAnhCrud);

    HinhAnhCrud updateHinhAnh(HinhAnhCrud hinhAnhCrud);
}
