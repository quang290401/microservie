package com.example.duantest.service;



import com.example.duantest.dto.MauSacDTO;

import java.util.List;

public interface MauSacService {
    List<MauSacDTO> getAllMauSac();

    MauSacDTO addMauSac̣̣(MauSacDTO mauSacDTO);
    MauSacDTO upDateMauSac(MauSacDTO mauSacDTO);
}
