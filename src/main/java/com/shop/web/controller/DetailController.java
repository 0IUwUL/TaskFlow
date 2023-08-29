package com.shop.web.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.web.Status;
import com.shop.web.dto.DetailsDTO;
import com.shop.web.models.Details;
import com.shop.web.service.DetailService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller

public class DetailController {
    private DetailService detailService;
    private static final Map <Status, String> mapStatuswithString = new LinkedHashMap <>();
    private LocalDateTime created_on;

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
    public String visitAllDetails(Model model, HttpSession session){
        List<DetailsDTO> tasks = detailService.findallTasks();
        session.setAttribute("lastVisit", "/detail");
        model.addAttribute("detail", tasks);
        model.addAttribute("view_all", true);
        model.addAttribute("status_task", mapStatuswithString);
        return "CRUD-detail/view";
    }

    //display list of tasks of user
    @GetMapping("/detail/{userId}")
    public String visitDetail(@PathVariable("userId") Long userId, Model model, HttpSession session){
        session.setAttribute("lastVisit", "/detail/"+userId);
        List<DetailsDTO> tasks = detailService.findDetailByUser(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("detail", tasks);
        model.addAttribute("view_all", false);
        model.addAttribute("status_task", mapStatuswithString);
        return "CRUD-detail/view";
    }

    //display input form
    @GetMapping("/detail/{userId}/new")
    public String createDetail(@PathVariable("userId") Long userId, Model model){
        Details detail = new Details();
        model.addAttribute("userId", userId);
        model.addAttribute("detail", detail);

        model.addAttribute("status_task", mapStatuswithString);
        return "CRUD-detail/insert";
    }

    //receive insert
    @PostMapping("/insert_detail/{userId}")
    public String insert(@PathVariable("userId") long userId, @Valid @ModelAttribute("detail") DetailsDTO detailDTO, 
                                        BindingResult result, Model model, RedirectAttributes redirectatts){
        if(result.hasErrors()){
            model.addAttribute("detail", detailDTO);
            model.addAttribute("status_task", mapStatuswithString);
            return "CRUD-detail/insert";
        }
        detailService.createDetail(userId, detailDTO);
        redirectatts.addFlashAttribute("cond", true);
        redirectatts.addFlashAttribute("status", "success");
        redirectatts.addFlashAttribute("message", "Detail "+ detailDTO.getTitle() +" inserted successfully.");
        return "redirect:/detail/"+userId;
    }

    //display edit form
    @GetMapping("/detail/{detailId}/edit")
    public String editDetail(@PathVariable("detailId") Long detailId, Model model){
        DetailsDTO detailsDTO = detailService.findById(detailId);
        if(detailsDTO != null)
            created_on = detailsDTO.getCreatedOn();
        model.addAttribute("detail", detailsDTO);
        model.addAttribute("detailId", detailId);
        model.addAttribute("status_task", mapStatuswithString);
        return "CRUD-detail/edit";
    }

    @PostMapping("/edit_detail/{detailId}")
    public String updateUser(@PathVariable("detailId") long detailId, 
                             @Valid @ModelAttribute("detail") DetailsDTO detailDto,
                             BindingResult result, Model model,
                             HttpSession session, RedirectAttributes redirectatts){
        if(result.hasErrors()){
            model.addAttribute("detail", detailDto);
            model.addAttribute("status_task", mapStatuswithString);
            return "CRUD-detail/edit";
        }
        DetailsDTO detail_user = detailService.findById(detailId);
        detailDto.setId(detailId);
        detailDto.setUser(detail_user.getUser());
        detailDto.setCreatedOn(created_on);
        detailDto.setUpdatedOn(LocalDateTime.now());
        detailService.updateDetail(detailDto);

        redirectatts.addFlashAttribute("cond", true);
        redirectatts.addFlashAttribute("status", "warning");
        redirectatts.addFlashAttribute("message", "Detail "+ detail_user.getTitle() +" updated successfully.");
        return "redirect:"+(String) session.getAttribute("lastVisit");
    }

    @GetMapping("/detail/{detailId}/delete")
    public String detailDelete(@PathVariable("detailId") long detailId, RedirectAttributes redirect,
                                HttpSession session, RedirectAttributes redirectatts) {
        String title = detailService.deleteTask(detailId);
        redirectatts.addFlashAttribute("cond", true);
        redirectatts.addFlashAttribute("status", "danger");
        redirectatts.addFlashAttribute("message", "Detail "+ title +" updated successfully.");
        return "redirect:"+(String)session.getAttribute("lastVisit");
    }
    
}
