package com.example.duantest.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KieuDangController {
    @GetMapping("/kieu-dang")
    public String home() {

        return "admin/adminWeb/KieuDang";
    }
}
