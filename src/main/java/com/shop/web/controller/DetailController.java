package com.shop.web.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.web.Status;
import com.shop.web.dto.DetailsDTO;
import com.shop.web.models.Details;
import com.shop.web.service.DetailService;

import jakarta.validation.Valid;


@Controller

public class DetailController {
    private DetailService detailService;
    private static final Map <Status, String> mapStatuswithString = new LinkedHashMap <>();
    
    static{
        mapStatuswithString.put(Status.TODO, "To Do");
        mapStatuswithString.put(Status.DONE, "Done");
        mapStatuswithString.put(Status.INPROGRESS, "In Progress");
    }

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    //display all of tasks
    @GetMapping("/detail")
    public String visitAllDetails(Model model){
        List<DetailsDTO> tasks = detailService.findallTasks();
        model.addAttribute("detail", tasks);
        model.addAttribute("view_all", true);
        return "CRUD-detail/view";
    }

    //display list of tasks of user
    @GetMapping("/detail/{userId}")
    public String visitDetail(@PathVariable("userId") Long userId, Model model){
        DetailsDTO tasks = detailService.findDetailByUser(userId);
        
        model.addAttribute("userId", userId);
        model.addAttribute("detail", tasks);
        model.addAttribute("view_all", false);
        model.addAttribute("status", mapStatuswithString);
        return "CRUD-detail/view";
    }

    //display input form
    @GetMapping("/detail/{userId}/new")
    public String createDetail(@PathVariable("userId") Long userId, Model model){
        Details detail = new Details();
        model.addAttribute("userId", userId);
        model.addAttribute("detail", detail);

        model.addAttribute("status", mapStatuswithString);
        return "CRUD-detail/insert";
    }

    //receive insert
    @PostMapping("/insert_task/{userId}")
    public String insert(@PathVariable("userId") long userId, @Valid @ModelAttribute("detail") DetailsDTO detailDTO, BindingResult result, Model model){
        System.out.println(detailDTO);
        if(result.hasErrors()){
            model.addAttribute("detail", detailDTO);
            return "CRUD-detail/insert";
        }
        detailService.createDetail(userId, detailDTO);
        return "redirect:/detail/"+userId;
    }
}
