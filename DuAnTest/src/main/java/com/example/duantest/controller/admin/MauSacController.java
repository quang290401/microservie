package com.example.duantest.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MauSacController {
    @GetMapping("/mau-sac")
    public String home() {

        return "admin/adminWeb/MauSac";
    }
}