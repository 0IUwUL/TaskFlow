package com.shop.web.controller;

import java.time.LocalDateTime;
import java.util.List;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.web.dto.UserDTO;
import com.shop.web.models.User;
import com.shop.web.models.UserEntity;
import com.shop.web.security.SecurityUtil;
import com.shop.web.service.UserEntityService;
import com.shop.web.service.UserService;

import jakarta.validation.Valid;

@Controller

public class UserController {
    private UserService userService;
    private LocalDateTime created_on;
    private UserEntityService userEntityService;

    public UserController(UserService userService, UserEntityService userEntityService) {
        this.userService = userService;
        this.userEntityService = userEntityService;
    }

    //read
    @GetMapping("/users")
    public String listUsers(Model model){
        UserEntity user_entity = new UserEntity();
        List<UserDTO> users = userService.findallUsers();
        String name = SecurityUtil.getSessionUser();
        if(name!=null){
            user_entity = userEntityService.findByUsername(name);
            model.addAttribute("user_entity", user_entity);
        }
        model.addAttribute("users", users);
        model.addAttribute("user_entity", user_entity);

        return "CRUD-user/users-list";
    }

    @GetMapping("/search")
    public String userSearch(@RequestParam(value = "search") String query, Model model){
        if (query.isEmpty()){
            return "redirect:/users";
        }
        List<UserDTO> users = userService.searchUsers(query);
        model.addAttribute("users", users);
        return "CRUD-user/users-list";
    }

    //insert

    @GetMapping("/insert")
    public String showInsertForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "CRUD-user/insert";
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("user") UserDTO userDto, BindingResult result, 
                                Model model, RedirectAttributes redirect){
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "CRUD-user/insert";
        }
        userService.save(userDto);
        redirect.addFlashAttribute("cond", true);
        redirect.addFlashAttribute("status", "success");
        redirect.addFlashAttribute("message", "User "+ userDto.getName() +" inserted successfully.");
        return "redirect:/users";
    }
    
    //edit

    @GetMapping("/user/edit/{userId}")
    public String getInformation(@PathVariable("userId") long userId, Model model){
        UserDTO user = userService.findUserById(userId);
        created_on = user.getCreatedOn();
        model.addAttribute("user", user);
        return "CRUD-user/edit";
    }

    @PostMapping("/user/edit/{userId}")
    public String updateUser(@PathVariable("userId") long userId, 
                             @Valid @ModelAttribute("user") UserDTO userDto,
                             BindingResult result, Model model, RedirectAttributes redirect){
        if(result.hasErrors()){
            return "CRUD-user/edit";
        }
        userDto.setId(userId);
        userDto.setCreatedOn(created_on);
        userDto.setUpdatedOn(LocalDateTime.now());
        userService.updateUser(userDto);
        redirect.addFlashAttribute("cond", true);
        redirect.addFlashAttribute("status", "warning");
        redirect.addFlashAttribute("message", "User "+ userDto.getName() +" updated successfully.");
        return "redirect:/users";
    }

    //delete

    @GetMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable("userId") long userId, RedirectAttributes redirectatts){
        String name = userService.delete(userId);
        redirectatts.addFlashAttribute("cond", true);
        redirectatts.addFlashAttribute("status", "warning");
        redirectatts.addFlashAttribute("message", "User "+ name +" deleted successfully.");
        return "redirect:/users";
    }


}
