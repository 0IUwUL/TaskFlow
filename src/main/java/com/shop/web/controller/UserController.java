package com.shop.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.web.dto.UserDTO;
import com.shop.web.service.UserService;

@Controller

public class UserController {
    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model){
        List<UserDTO> users = userService.findallUsers();
        model.addAttribute("users", users);

        return "users-list";
    }

}
