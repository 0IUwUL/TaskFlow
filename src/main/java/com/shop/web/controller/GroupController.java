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

import com.shop.web.dto.GroupDTO;
import com.shop.web.models.Group;
import com.shop.web.models.UserEntity;
import com.shop.web.security.SecurityUtil;
import com.shop.web.service.UserEntityService;
import com.shop.web.service.GroupService;

import jakarta.validation.Valid;

@Controller

public class GroupController {
    private GroupService groupService;
    private LocalDateTime created_on;
    private UserEntityService userEntityService;

    public GroupController(GroupService groupService, UserEntityService userEntityService) {
        this.groupService = groupService;
        this.userEntityService = userEntityService;
    }

    //read
    @GetMapping("/groups")
    public String listUsers(Model model){
        UserEntity user_entity = new UserEntity();
        List<GroupDTO> groups = groupService.findallUsers();
        String name = SecurityUtil.getSessionUser();
        if(name!=null){
            user_entity = userEntityService.findByUsername(name);
            model.addAttribute("user_entity", user_entity);
        }
        model.addAttribute("groups", groups);
        model.addAttribute("user_entity", user_entity);

        return "CRUD-group/groups-list";
    }

    @GetMapping("/search")
    public String userSearch(@RequestParam(value = "search") String query, Model model){
        if (query.isEmpty()){
            return "redirect:/groups";
        }
        List<GroupDTO> groups = groupService.searchGroups(query);
        model.addAttribute("groups", groups);
        return "CRUD-group/groups-list";
    }

    //insert

    @GetMapping("/insert")
    public String showInsertForm(Model model){
        Group group = new Group();
        model.addAttribute("group", group);
        return "CRUD-group/insert";
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("group") GroupDTO userDto, BindingResult result, 
                                Model model, RedirectAttributes redirect){
        if(result.hasErrors()){
            model.addAttribute("group", userDto);
            return "CRUD-group/insert";
        }
        groupService.save(userDto);
        redirect.addFlashAttribute("cond", true);
        redirect.addFlashAttribute("status", "success");
        redirect.addFlashAttribute("message", "Group "+ userDto.getName() +" inserted successfully.");
        return "redirect:/groups";
    }
    
    //edit

    @GetMapping("/group/edit/{userId}")
    public String getInformation(@PathVariable("userId") long userId, Model model){
        GroupDTO group = groupService.findUserById(userId);
        created_on = group.getCreatedOn();
        model.addAttribute("group", group);
        return "CRUD-group/edit";
    }

    @PostMapping("/group/edit/{userId}")
    public String updateUser(@PathVariable("userId") long userId, 
                             @Valid @ModelAttribute("group") GroupDTO userDto,
                             BindingResult result, Model model, RedirectAttributes redirect){
        if(result.hasErrors()){
            return "CRUD-group/edit";
        }
        userDto.setId(userId);
        userDto.setCreatedOn(created_on);
        userDto.setUpdatedOn(LocalDateTime.now());
        groupService.updateUser(userDto);
        redirect.addFlashAttribute("cond", true);
        redirect.addFlashAttribute("status", "warning");
        redirect.addFlashAttribute("message", "Group "+ userDto.getName() +" updated successfully.");
        return "redirect:/groups";
    }

    //delete

    @GetMapping("/group/delete/{userId}")
    public String deleteUser(@PathVariable("userId") long userId, RedirectAttributes redirectatts){
        String name = groupService.delete(userId);
        redirectatts.addFlashAttribute("cond", true);
        redirectatts.addFlashAttribute("status", "warning");
        redirectatts.addFlashAttribute("message", "Group "+ name +" deleted successfully.");
        return "redirect:/groups";
    }


}
