package com.vilas.hungerHub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Demo {

    @RequestMapping("/hello")
    public String demo(){
        return "Hello world";
    }

}
