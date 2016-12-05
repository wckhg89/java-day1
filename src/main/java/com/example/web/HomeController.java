package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 강홍구 on 2016-11-28.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public String home() {
        return "redirect:/questions";
    }
}
