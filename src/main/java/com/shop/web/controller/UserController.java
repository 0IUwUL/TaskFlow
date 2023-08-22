package com.shop.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.web.dto.UserDTO;
import com.shop.web.models.User;
import com.shop.web.service.UserService;

import jakarta.validation.Valid;

@Controller

public class UserController {
    private UserService userService;
    private LocalDateTime created_on;

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

    @GetMapping("/insert")
    public String showInsertForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "CRUD/insert";
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("user") UserDTO userDto, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println(result);
            model.addAttribute("user", userDto);
            return "CRUD/insert";
        }
        userService.save(userDto);
        return "redirect:/users";
    }
    
    @GetMapping("/edit/{userId}")
    public String getInformation(@PathVariable("userId") long userId, Model model){
        UserDTO user = userService.findUserById(userId);
        created_on = user.getCreatedOn();
        model.addAttribute("user", user);
        return "CRUD/edit";
    }

    @PostMapping("/edit/{userId}")
    public String updateUser(@PathVariable("userId") long userId, 
                             @Valid @ModelAttribute("user") UserDTO userDto,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            return "CRUD/edit";
        }
        userDto.setId(userId);
        userDto.setCreatedOn(created_on);
        userDto.setUpdatedOn(LocalDateTime.now());
        userService.updateUser(userDto);
        return "redirect:/users";
    }
}
