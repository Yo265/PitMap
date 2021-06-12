package com.example.roadpitmap.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping
    public String index(){
        return "/index";
    }

    @GetMapping("/add")
    public String newPit(){
        return "/newpit";
    }

    @GetMapping("/stats")
    public String stats(){
        return "/stats";
    }
}
