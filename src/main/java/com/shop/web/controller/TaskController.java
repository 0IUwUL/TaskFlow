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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import com.shop.web.Status;
import com.shop.web.dto.TaskDTO;
import com.shop.web.models.Task;
import com.shop.web.models.Type;
import com.shop.web.models.Users;
import com.shop.web.security.SecurityUtil;
import com.shop.web.service.TaskService;
import com.shop.web.service.TypeService;
import com.shop.web.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller

public class TaskController {
    private TaskService taskService;
    private LocalDateTime created_on;
    private UserService userService;
    private TypeService typeService;
    private List<Type> types;

    public TaskController(TaskService taskService, UserService userService,
                            TypeService typeService) {
        this.taskService = taskService;
        this.userService = userService;
        this.typeService = typeService;
    }

    //display all of tasks
    @GetMapping("/task")
    public String visitAllTasks(Model model, HttpSession session){
        Users users = new Users();
        List<TaskDTO> tasks = taskService.findallTasks();
        String name = SecurityUtil.getSessionUser();
        if(name!=null){
            users = userService.findByUsername(name);
            model.addAttribute("users", users);
            model.addAttribute("userId", users.getId());
        }
        model.addAttribute("users", users);

        types = typeService.getAllTypes();
        
        session.setAttribute("lastVisit", "/task");
        model.addAttribute("task", tasks);
        model.addAttribute("view_all", true);
        model.addAttribute("status_task", types);
        return "CRUD-task/view";
    }

    //display input form
    @GetMapping("/task/{userId}/new")
    public String createTask(@PathVariable("userId") Long userId, Model model){
        Task task = new Task();
        types = typeService.getAllTypes();
        model.addAttribute("userId", userId);
        model.addAttribute("task", task);
        model.addAttribute("status_task", types);
        return "CRUD-task/insert";
    }

    //receive insert
    @PostMapping("/insert_task/{userId}")
    public String insert(@PathVariable("userId") long userId, @Valid @ModelAttribute("task") TaskDTO taskDTO, 
                                        BindingResult result, Model model, RedirectAttributes redirectatts){
        if(result.hasErrors()){
            model.addAttribute("task", taskDTO);
            // model.addAttribute("status_task", mapStatuswithString);
            return "CRUD-task/insert";
        }
        taskService.createTask(userId, taskDTO);
        redirectatts.addFlashAttribute("cond", true);
        redirectatts.addFlashAttribute("status", "success");
        redirectatts.addFlashAttribute("message", "Task "+ taskDTO.getTitle() +" inserted successfully.");
        return "redirect:/task";
    }

    //display edit form
    @GetMapping("/task/{taskId}/edit")
    public String editTask(@PathVariable("taskId") Long taskId, Model model){
        TaskDTO tasksDTO = taskService.findById(taskId);
        types = typeService.getAllTypes();
        if(tasksDTO != null)
            created_on = tasksDTO.getCreatedOn();
        model.addAttribute("task", tasksDTO);
        model.addAttribute("taskId", taskId);
        model.addAttribute("status_task", types);
        return "CRUD-task/edit";
    }

    @PostMapping("/edit_task/{taskId}")
    public String updateTask(@PathVariable("taskId") long taskId, 
                             @Valid @ModelAttribute("task") TaskDTO taskDto,
                             BindingResult result, Model model,
                             HttpSession session, RedirectAttributes redirectatts){
        if(result.hasErrors()){
            model.addAttribute("task", taskDto);
            // model.addAttribute("status_task", mapStatuswithString);
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
