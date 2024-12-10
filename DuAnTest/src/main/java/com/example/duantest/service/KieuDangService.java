package com.example.duantest.service;



import com.example.duantest.dto.KieuDangDTO;

import java.util.List;

public interface KieuDangService {
    List<KieuDangDTO> getAllKieuDang();
    KieuDangDTO addKieuDang(KieuDangDTO kieuDangDTO);
    KieuDangDTO updateKieuDang(KieuDangDTO kieuDangDTO);
}
