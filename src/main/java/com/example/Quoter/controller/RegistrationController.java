package com.example.Quoter.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Quoter.domain.Role;
import com.example.Quoter.domain.User;
import com.example.Quoter.repos.UserRepo;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDB = this.userRepo.findByUsername(user.getUsername());
        
        if (userFromDB != null) {
            model.put("message", "User already exists!");
            return "registration";
        }
        
        // save the user into the database if there is no users with the same username.
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        this.userRepo.save(user);
        
        return "redirect:/login";
    }
}