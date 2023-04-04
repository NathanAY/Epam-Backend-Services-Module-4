package com.epam.task4.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class PublicController {


    @GetMapping("info")
    public String home(){
        return "index";
    }

    @GetMapping("about")
    public String about(){
        return "about";
    }

}
