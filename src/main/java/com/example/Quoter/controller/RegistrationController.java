package com.example.Quoter.controller;

import java.util.Map;

import javax.validation.Valid;

import com.example.Quoter.domain.User;
import com.example.Quoter.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    
    @PostMapping("/registration")
    public String addUser(
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        // first, make sure that the password matches.
        if (user.getPassword() != null && !user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("passwordError", "Passwords are different");
        }

        // then, get errors if any.
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);

            return "registration";
        }

        if (!this.userService.addUser(user)) {
            model.addAttribute("usernameError", "User already exists!");
            return "registration";
        }
        
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(
            @PathVariable String code,
            Model model
    ) {
        boolean isActivated = this.userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated!");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }
}
