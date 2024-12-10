package com.example.duantest.service;



import com.example.duantest.dto.KichCoDTO;

import java.util.List;

public interface KichCoService   {
    List<KichCoDTO> getAllKichCo();
    KichCoDTO addKichCo(KichCoDTO kichCoDTO);
    KichCoDTO updateKichCo(KichCoDTO kichCoDTO);

}
