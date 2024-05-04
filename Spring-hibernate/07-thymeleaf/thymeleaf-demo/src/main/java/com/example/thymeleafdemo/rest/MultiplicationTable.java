package com.example.thymeleafdemo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MultiplicationTable {
    @GetMapping("/show/{x}")
    public String showTable(@PathVariable int x, Model model){
        model.addAttribute("number", x);
        // return Thymeleaf file name
        return "multiplication-table";
    }
}
