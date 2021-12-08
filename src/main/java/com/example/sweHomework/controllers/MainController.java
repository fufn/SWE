package com.example.sweHomework.controllers;

import com.example.sweHomework.entities.Users;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping(value = "/index")
    public String index(){
        return ("index");
    }

    @GetMapping(value = "/loginpage")
    public String loginPage(Model model){
        return "main";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        return "index";
    }

    private Users getUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            Users user = (Users)authentication.getPrincipal();
            return user;
        } else {
            return null;
        }

    }

}
