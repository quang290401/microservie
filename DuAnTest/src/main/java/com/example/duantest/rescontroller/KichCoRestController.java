package com.example.duantest.rescontroller;

import com.example.duantest.dto.KichCoDTO;
import com.example.duantest.service.KichCoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kich-co")
public class KichCoRestController {
    private final KichCoService kichCoService;

    @GetMapping("/getAll")
    public List<KichCoDTO> getAllMauSac() {
        return kichCoService.getAllKichCo();
    }

    @PostMapping()
    public KichCoDTO addMauSac(@RequestBody KichCoDTO kichCoDTO) {
        return kichCoService.addKichCo(kichCoDTO);

    }

    @PutMapping()
    public KichCoDTO  uppDateKieuDang(@RequestBody KichCoDTO kichCoDTO) {
        return kichCoService.updateKichCo(kichCoDTO);


    }
}