package com.factory13.audiox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AudioXController {

    @GetMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("test", "value");
        return "index";
    }

}
