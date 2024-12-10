package com.example.duantest.rescontroller;

import com.example.duantest.dto.MauSacDTO;
import com.example.duantest.service.MauSacService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/mau-sac")
public class MauSacRestController {
    private final MauSacService mauSacService;

    @GetMapping("/getAll")
    public List<MauSacDTO> getAllMauSac() {
        return mauSacService.getAllMauSac();
    }
    @PostMapping()
    public MauSacDTO addMauSac(@RequestBody MauSacDTO mauSacDTO){
        return mauSacService.addMauSac̣̣(mauSacDTO);

    }
    @PutMapping()
    public MauSacDTO uppDateMauSac(@RequestBody  MauSacDTO mauSacDTO){
        return mauSacService.upDateMauSac( mauSacDTO);

    }
}
