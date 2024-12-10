package com.example.duantest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DetailController {
    @GetMapping("/detailSP")
    public String home(){


        return "web/DetailSP";
    }
}
