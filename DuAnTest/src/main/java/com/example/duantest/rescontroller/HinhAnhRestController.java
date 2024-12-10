package com.example.duantest.rescontroller;

import com.example.duantest.common.Appcontants;
import com.example.duantest.dto.HinhAnhCrud;
import com.example.duantest.dto.HinhAnhDTO;
import com.example.duantest.service.HinhAnhService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hinh-anh")
public class HinhAnhRestController {
    private final HinhAnhService service;

    @GetMapping()
    public Page<HinhAnhDTO> getAllProducts(
            @RequestParam(value = "pageNo", defaultValue = Appcontants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Appcontants.DEFAULT_TOTAL_NUMBER, required = false) int pageSize
    ) {
        return service.getAllHinhAnh(pageNo, pageSize);
    }
    @PostMapping()
    public HinhAnhCrud addSanPham(@RequestBody HinhAnhCrud hinhAnhCrud){
        return service.addHinhAnh(hinhAnhCrud);

    }
    @PutMapping("/update")
    public HinhAnhCrud upDateHinhAnh(@RequestBody HinhAnhCrud hinhAnhCrud){
        return service.updateHinhAnh(hinhAnhCrud);

    }
}
