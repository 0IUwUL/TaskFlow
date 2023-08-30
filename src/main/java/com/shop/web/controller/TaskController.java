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
import com.shop.web.dto.TaskDTO;
import com.shop.web.models.Task;
import com.shop.web.models.UserEntity;
import com.shop.web.security.SecurityUtil;
import com.shop.web.service.TaskService;
import com.shop.web.service.UserEntityService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller

public class TaskController {
    private TaskService taskService;
    private static final Map <Status, String> mapStatuswithString = new LinkedHashMap <>();
    private LocalDateTime created_on;
    private UserEntityService userEntityService;
    static{
        mapStatuswithString.put(Status.TODO, "To Do");
        mapStatuswithString.put(Status.DONE, "Done");
        mapStatuswithString.put(Status.INPROGRESS, "In Progress");
    }

    public TaskController(TaskService taskService, UserEntityService userEntityService) {
        this.taskService = taskService;
        this.userEntityService = userEntityService;
    }

    //display all of tasks
    @GetMapping("/task")
    public String visitAllTasks(Model model, HttpSession session){
        UserEntity user_entity = new UserEntity();
        List<TaskDTO> tasks = taskService.findallTasks();
        String name = SecurityUtil.getSessionUser();
        if(name!=null){
            user_entity = userEntityService.findByUsername(name);
            model.addAttribute("user_entity", user_entity);
        }
        model.addAttribute("user_entity", user_entity);

        session.setAttribute("lastVisit", "/task");
        model.addAttribute("task", tasks);
        model.addAttribute("view_all", true);
        model.addAttribute("status_task", mapStatuswithString);
        return "CRUD-task/view";
    }

    //display list of tasks of user
    @GetMapping("/task/{userId}")
    public String visitTask(@PathVariable("userId") Long userId, Model model, HttpSession session){
        UserEntity user_entity = new UserEntity();
        session.setAttribute("lastVisit", "/task/"+userId);
        List<TaskDTO> tasks = taskService.findTaskByUser(userId);
        String name = SecurityUtil.getSessionUser();
        if(name!=null){
            user_entity = userEntityService.findByUsername(name);
            model.addAttribute("user_entity", user_entity);
        }
        model.addAttribute("user_entity", user_entity);

        model.addAttribute("userId", userId);
        model.addAttribute("task", tasks);
        model.addAttribute("view_all", false);
        model.addAttribute("status_task", mapStatuswithString);
        return "CRUD-task/view";
    }

    //display input form
    @GetMapping("/task/{userId}/new")
    public String createTask(@PathVariable("userId") Long userId, Model model){
        Task task = new Task();
        model.addAttribute("userId", userId);
        model.addAttribute("task", task);

        model.addAttribute("status_task", mapStatuswithString);
        return "CRUD-task/insert";
    }

    //receive insert
    @PostMapping("/insert_task/{userId}")
    public String insert(@PathVariable("userId") long userId, @Valid @ModelAttribute("task") TaskDTO taskDTO, 
                                        BindingResult result, Model model, RedirectAttributes redirectatts){
        if(result.hasErrors()){
            model.addAttribute("task", taskDTO);
            model.addAttribute("status_task", mapStatuswithString);
            return "CRUD-task/insert";
        }
        taskService.createTask(userId, taskDTO);
        redirectatts.addFlashAttribute("cond", true);
        redirectatts.addFlashAttribute("status", "success");
        redirectatts.addFlashAttribute("message", "Task "+ taskDTO.getTitle() +" inserted successfully.");
        return "redirect:/task/"+userId;
    }

    //display edit form
    @GetMapping("/task/{taskId}/edit")
    public String editTask(@PathVariable("taskId") Long taskId, Model model){
        TaskDTO tasksDTO = taskService.findById(taskId);
        if(tasksDTO != null)
            created_on = tasksDTO.getCreatedOn();
        model.addAttribute("task", tasksDTO);
        model.addAttribute("taskId", taskId);
        model.addAttribute("status_task", mapStatuswithString);
        return "CRUD-task/edit";
    }

    @PostMapping("/edit_task/{taskId}")
    public String updateTask(@PathVariable("taskId") long taskId, 
                             @Valid @ModelAttribute("task") TaskDTO taskDto,
                             BindingResult result, Model model,
                             HttpSession session, RedirectAttributes redirectatts){
        if(result.hasErrors()){
            model.addAttribute("task", taskDto);
            model.addAttribute("status_task", mapStatuswithString);
            return "CRUD-task/edit";
        }
        TaskDTO task_user = taskService.findById(taskId);
        taskDto.setId(taskId);
        taskDto.setUser(task_user.getUser());
        taskDto.setCreatedOn(created_on);
        taskDto.setUpdatedOn(LocalDateTime.now());
        taskService.updateTask(taskDto);

        redirectatts.addFlashAttribute("cond", true);
        redirectatts.addFlashAttribute("status", "warning");
        redirectatts.addFlashAttribute("message", "Task "+ task_user.getTitle() +" updated successfully.");
        return "redirect:"+(String) session.getAttribute("lastVisit");
    }

    @GetMapping("/task/{taskId}/delete")
    public String taskDelete(@PathVariable("taskId") long taskId, RedirectAttributes redirect,
                                HttpSession session, RedirectAttributes redirectatts) {
        String title = taskService.deleteTask(taskId);
        redirectatts.addFlashAttribute("cond", true);
        redirectatts.addFlashAttribute("status", "danger");
        redirectatts.addFlashAttribute("message", "Task "+ title +" updated successfully.");
        return "redirect:"+(String)session.getAttribute("lastVisit");
    }
    
}
