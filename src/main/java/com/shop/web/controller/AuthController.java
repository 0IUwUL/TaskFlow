package com.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.web.dto.RegistrationDTO;
import com.shop.web.models.Users;
import com.shop.web.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String landingpage(){
        return "landing";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("user", registrationDTO);
        return "auth/registration";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "auth/login";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") RegistrationDTO registrationDTO,
                                BindingResult result, Model model, RedirectAttributes redirect){
        Users existingUsersEmail = userService.findByEmail(registrationDTO.getEmail());
        
        if (existingUsersEmail != null && existingUsersEmail.getEmail() != null && !existingUsersEmail.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }

        Users existingUsersName = userService.findByUsername(registrationDTO.getUsername());
        if (existingUsersName != null && existingUsersName.getUsername() != null && !existingUsersName.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }
        if(result.hasErrors()){
            model.addAttribute("user", registrationDTO);
            return "auth/registration";
        }
        userService.saveUser(registrationDTO);
        redirect.addFlashAttribute("cond", true);
        redirect.addFlashAttribute("status", "success");
        redirect.addFlashAttribute("message", "Registered successfully");
        return "redirect:/";
    }
    
}

