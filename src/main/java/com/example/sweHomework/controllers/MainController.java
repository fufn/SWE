package com.example.sweHomework.controllers;

import com.example.sweHomework.entities.Users;
import com.example.sweHomework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String index(){
        return ("index");
    }

    @GetMapping(value = "/loginpage")
    public String loginPage(Model model){
        return "login";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        return "index";
    }

    @PostMapping(value = "/toregister")
    public String toRegister(@RequestParam(name = "user_email") String email,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "user_repassword") String rePassword,
                             @RequestParam(name = "user_full_name") String fullName){

        if(password.equals(rePassword)){

            Users user = new Users();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setFull_name(fullName);

            if(userService.addUser(user)!=null){

                return "redirect:/registerpage?success";

            }

            return "redirect:/registerpage?emailerror";

        }

        return "redirect:/registerpage?passworderror";

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
