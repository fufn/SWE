package com.example.sweHomework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String test(){
        return ("test");
    }

    @GetMapping(value = "/secondPage")
    public String secondPage(){
        return ("secondPage");
    }

}
