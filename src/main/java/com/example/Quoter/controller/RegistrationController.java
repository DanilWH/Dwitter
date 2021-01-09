package com.example.Quoter.controller;

import com.example.Quoter.domain.User;
import com.example.Quoter.domain.dto.CaptchaResponseDto;
import com.example.Quoter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("${recaptcha.secret}")
    private String secret;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    
    @PostMapping("/registration")
    public String addUser(
            @RequestParam String confirmPassword,
            @RequestParam("g-recaptcha-response") String gRecaptchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        // make an API request to ensure the captcha has been filled (token is valid).
        String url = String.format(CAPTCHA_URL, this.secret, gRecaptchaResponse);
        CaptchaResponseDto response = this.restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        // verify the response token
        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }

        // check if the password confirmation isn't empty.
        boolean isConfirmPasswordEmpty = StringUtils.isEmpty(confirmPassword);

        if (isConfirmPasswordEmpty) {
            model.addAttribute("confirmPasswordError", "Password confirmation can't be empty");
        }

        // make sure that the password matches.
        if (user.getPassword() != null && !user.getPassword().equals(confirmPassword)) {
            model.addAttribute("passwordError", "Passwords are different");
        }

        // get errors if any.
        if (!response.isSuccess() || isConfirmPasswordEmpty || bindingResult.hasErrors()) {
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
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated!");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }
}
