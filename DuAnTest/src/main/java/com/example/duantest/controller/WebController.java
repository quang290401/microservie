package com.example.duantest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/trang-chu")
    public String home(){
        return "web/web.html";
    }

}