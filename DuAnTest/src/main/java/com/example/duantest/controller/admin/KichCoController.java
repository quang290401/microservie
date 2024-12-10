package com.example.duantest.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KichCoController {
    @GetMapping("/kich-co")
    public String home() {

        return "admin/adminWeb/KichCo";
    }
}