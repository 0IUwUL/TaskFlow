package com.shop.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.web.dto.UserDTO;
import com.shop.web.models.User;
import com.shop.web.service.UserService;

@Controller

public class UserController {
    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String landingpage(){
        return "landing";
    }

    @GetMapping("/users")
    public String listUsers(Model model){
        List<UserDTO> users = userService.findallUsers();
        model.addAttribute("users", users);

        return "users-list";
    }

    @PostMapping("/insert")
    public String insert(@Validated @ModelAttribute("users") User user){
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{userId}")
    public String getInformation(@PathVariable("userId") long userId, Model model){
        UserDTO user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit/{userId}")
    public String updateUser(@PathVariable("userId") long userId, @ModelAttribute("user") UserDTO userDto){
        userDto.setId(userId);
        userService.updateUser(userDto);
        return "redirect:/users";
    }
}
