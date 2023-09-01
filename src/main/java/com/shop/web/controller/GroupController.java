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
import com.shop.web.models.Users;
import com.shop.web.security.SecurityUtil;
import com.shop.web.service.UserService;
import com.shop.web.service.GroupService;

import jakarta.validation.Valid;

@Controller

public class GroupController {
    private GroupService groupService;
    private LocalDateTime created_on;
    private UserService userService;

    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    //read
    @GetMapping("/groups")
    public String listGroups(Model model){
        Users users = new Users();
        String name = SecurityUtil.getSessionUser();
        if(name!=null){
            users = userService.findByUsername(name);
            model.addAttribute("users", users);
        }
        List<GroupDTO> own_group = groupService.findallGroups(users);
        List<GroupDTO> involve_group = groupService.findallInvolveGroups(users.getId());
        model.addAttribute("own_group", own_group);
        model.addAttribute("users", users);
        model.addAttribute("involve_group", involve_group);
        return "CRUD-group/groups-list";
    }

    @GetMapping("/search")
    public String groupSearch(@RequestParam(value = "search") String query, Model model){
        // Group groups = new Group();
        Users users = new Users();
        String name = SecurityUtil.getSessionUser();
        if(name!=null){
            users = userService.findByUsername(name);
            model.addAttribute("users", users);
        }
        if (query.isEmpty()){
            return "redirect:/groups";
        }
        List<GroupDTO> groups = groupService.searchGroups(query);
        for (GroupDTO groupDTO : groups) {
            if(groupDTO.getAdmin().getId() == users.getId())
                model.addAttribute("own_group", groups);
            else
                model.addAttribute("involve_group", groups);
        }
        model.addAttribute("users", users);
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
    public String updateGroup(@PathVariable("userId") long userId, 
                             @Valid @ModelAttribute("group") GroupDTO groupDTO,
                             BindingResult result, Model model, RedirectAttributes redirect){
        if(result.hasErrors()){
            return "CRUD-group/edit";
        }
        groupDTO.setId(userId);
        groupDTO.setCreatedOn(created_on);
        groupDTO.setUpdatedOn(LocalDateTime.now());
        groupService.updateGroup(groupDTO);
        redirect.addFlashAttribute("cond", true);
        redirect.addFlashAttribute("status", "warning");
        redirect.addFlashAttribute("message", "Group "+ groupDTO.getName() +" updated successfully.");
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
