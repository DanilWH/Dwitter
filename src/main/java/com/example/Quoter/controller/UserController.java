package com.example.Quoter.controller;

import com.example.Quoter.domain.Role;
import com.example.Quoter.domain.User;
import com.example.Quoter.repos.UserRepo;
import com.example.Quoter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        
        return "userList";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        
        return "userEdit";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam Long userId
    ) {
        this.userService.saveUser(userId, username, form);

        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(
        @AuthenticationPrincipal User user,
        Model model
    ) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
        @AuthenticationPrincipal User user,
        @RequestParam String password,
        @RequestParam String email
    ) {
        this.userService.updateProfile(user, password, email);

        return "redirect:/user/profile";
    }

    @GetMapping("/subscribe/{userId}")
    public String subscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long userId
    ) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new NoResultException());

        this.userService.subscribe(currentUser, user);

        return "redirect:/user-quotes/" + user.getId();

    }

    @GetMapping("/unsubscribe/{userId}")
    public String unsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long userId
    ) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new NoResultException());

        this.userService.unsubscribe(currentUser, user);

        return "redirect:/user-quotes/" + user.getId();

    }

    @GetMapping("/{type}/{userId}/list")
    public String userList(
            @PathVariable String type,
            @PathVariable Long userId,
            Model model
    ) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new NoResultException());

        model.addAttribute("userChannel", user);
        model.addAttribute("type", type);

        if ("subscriptions".equals(type)) {
            model.addAttribute("users", user.getSubscribtions());
        } else if ("subscribers".equals(type)) {
            model.addAttribute("users", user.getSubscribers());
        }

        return "subscriptions";
    }
}
