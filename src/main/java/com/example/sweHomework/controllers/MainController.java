package com.example.sweHomework.controllers;

import com.example.sweHomework.entities.Roles;
import com.example.sweHomework.entities.Users;
import com.example.sweHomework.repositories.RoleRepository;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

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

    @GetMapping(value = "/register")
    public String register(Model model){
        return "register";
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
                             @RequestParam(name = "user_full_name") String fullName,
                             @RequestParam(name = "id_type") String id_type,
                             @RequestParam(name = "id_number") String id_number,
                             @RequestParam(name = "address") String address,
                             @RequestParam(name = "home_phone_number") String home_phone_number,
                             @RequestParam(name = "mobile_phone_number") String mobile_phone_number){

        if(password.equals(rePassword)){

            Users user = new Users();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setFull_name(fullName);
            user.setId_type(id_type);
            user.setId_number(id_number);
            user.setAddress(address);
            user.setHome_phone_number(home_phone_number);
            user.setMobile_phone_number(mobile_phone_number);

            if(userService.addUser(user)!=null){

                return "redirect:/register?success";

            }

            return "redirect:/register?emailerror";

        }

        return "redirect:/register?passworderror";

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
