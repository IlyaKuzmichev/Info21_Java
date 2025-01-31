package edu.school21.info21.controller;

import edu.school21.info21.dto.TasksDTO;
import edu.school21.info21.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping
    public String tasks(Model model) {
        List<TasksDTO> tasksList = tasksService.getAllTasks(); // Получаем список задач
        model.addAttribute("tasks", tasksList);
        model.addAttribute("activePage", "tasks");
        return "tasks";
    }
}
