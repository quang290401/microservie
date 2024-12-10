package com.example.duantest.rescontroller;

import com.example.duantest.dto.KieuDangDTO;
import com.example.duantest.service.KieuDangService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kieu-dang")
public class KieuDangRestController {
    private final KieuDangService kieuDangService;

    @GetMapping("/getAll")
    public List<KieuDangDTO> getAllKieuDang() {
        return kieuDangService.getAllKieuDang();
    }
    @PostMapping()
    public KieuDangDTO addKieuDang(@RequestBody KieuDangDTO kieuDangDTO){
        return kieuDangService.addKieuDang(kieuDangDTO);

    }
    @PutMapping()
    public KieuDangDTO uppDateKieuDang(@RequestBody  KieuDangDTO kieuDangDTO){
        return kieuDangService.updateKieuDang(kieuDangDTO);

    }

}
