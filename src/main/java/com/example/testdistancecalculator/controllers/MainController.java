package com.example.testdistancecalculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String hom( Model model) {
        model.addAttribute("title", "Начальная страница");
        return "start";
    }

}
