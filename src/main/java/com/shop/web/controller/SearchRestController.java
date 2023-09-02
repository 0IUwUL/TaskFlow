// package com.shop.web.controller;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.shop.web.dto.TaskDTO;
// import com.shop.web.service.TaskService;

// @RestController
// public class SearchRestController {
    
//     private TaskService taskService;

//     public SearchRestController(TaskService taskService) {
//         this.taskService = taskService;
//     }

//     @GetMapping("/search")
//     public Map<String, List> Search(@RequestParam(name = "query") String query, Model model){
//         Map<String, List> response = new HashMap<>();
//         if (query.isEmpty()){
//             response.put("message", Arrays.asList("No search input."));
//             return response;
//         }
//         List<TaskDTO> task = taskService.searchTask(query);
//         if(task.isEmpty()){
//             response.put("message", Arrays.asList("No task found."));
//             return response;
//         }
//         response.put("message", Arrays.asList(task));
//         // List<TaskDTO> task = taskService.searchTask(query);
//         // model.addAttribute("task", task);
//         return response;
//     }
// }
