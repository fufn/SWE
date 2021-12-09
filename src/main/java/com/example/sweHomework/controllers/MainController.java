package com.example.sweHomework.controllers;

import com.example.sweHomework.entities.*;
import com.example.sweHomework.repositories.HotelsRepository;
import com.example.sweHomework.repositories.ReservationRepository;
import com.example.sweHomework.repositories.RoleRepository;
import com.example.sweHomework.repositories.RoomTypeRepository;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private HotelsRepository hotelsRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private ReservationRepository reservationRepository;

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
        return "profile";
    }

    @GetMapping(value = "/myreservations")
    @PreAuthorize("isAuthenticated()")
    public String myreservations(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        List<Reservation> reservations = reservationRepository.findByUsers(getUser());
        model.addAttribute("reservations", reservations);
        return "myreservations";
    }

    @GetMapping(value = "/adminpanel")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String adminpanel(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);
        return "adminpanel";
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("isAuthenticated()")
    public String delete(Model model,
                         @RequestParam(name = "id") Long id){
        reservationRepository.deleteById(id);
        return "redirect:/adminpanel?success";
    }

    @GetMapping(value = "/makereservation")
    @PreAuthorize("isAuthenticated()")
    public String makereservation(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        List<Hotels> hotels = hotelsRepository.findAll();
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        model.addAttribute("hotels", hotels);
        model.addAttribute("roomtypes", roomTypes);
        return "makereservation";
    }

    @PostMapping(value = "/tomakereservation")
    @PreAuthorize("isAuthenticated()")
    public String tomakereservation(@RequestParam(name = "hotel_id") Long hotel_id,
                                @RequestParam(name = "room_type") Long room_type,
                                @RequestParam(name = "check_in_date") Date check_in_date,
                                @RequestParam(name = "check_out_date") Date check_out_date){

        Reservation reservation = new Reservation();
        reservation.setHotels(hotelsRepository.getById(hotel_id));
        reservation.setRoomType(roomTypeRepository.getById(room_type));
        reservation.setCheck_in(check_in_date);
        reservation.setCheck_out(check_out_date);
        reservation.setUsers(getUser());
        reservationRepository.save(reservation);

        return "redirect:/makereservation?success";

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

    @PostMapping(value = "/toupdateprofile")
    @PreAuthorize("isAuthenticated()")
    public String updateProfile(@RequestParam(name = "full_name") String fullName,
                                @RequestParam(name = "address") String address,
                                @RequestParam(name = "mobile_phone_number") String mobile_phone_number,
                                @RequestParam(name = "home_phone_number") String home_phone_number){

        Users currentUser = getUser();
        currentUser.setFull_name(fullName);
        currentUser.setAddress(address);
        currentUser.setHome_phone_number(home_phone_number);
        currentUser.setMobile_phone_number(mobile_phone_number);
        userService.updateUser(currentUser);

        return "redirect:/profile?success";

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
