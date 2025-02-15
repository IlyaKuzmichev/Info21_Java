package edu.school21.info21.controller;

import edu.school21.info21.dto.TasksDTO;
import edu.school21.info21.mappers.TasksMapper;
import edu.school21.info21.model.Tasks;
import edu.school21.info21.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService tasksService;
    private final TasksMapper tasksMapper;

    @Autowired
    public TasksController(TasksService tasksService, TasksMapper tasksMapper) {
        this.tasksService = tasksService;
        this.tasksMapper = tasksMapper;
    }

    @GetMapping
    public String tasks(Model model) {
        List<TasksDTO> tasksList = tasksService.getAllTasks(); // Получаем список задач
        model.addAttribute("tasks", tasksList);
        model.addAttribute("activePage", "tasks");
        return "tasks";
    }

    @GetMapping("/{title}")
    public String getTaskByTitle(@PathVariable String title, Model model) {
        Optional<Tasks> task = tasksService.getTaskByTitle(title);
        if (task.isPresent()) {
            TasksDTO taskDTO = tasksMapper.toDTO(task.get());
            model.addAttribute("task", taskDTO);
            model.addAttribute("activePage", "tasks");
            return "task";
        } else {
            model.addAttribute("error", "Task with title " + title + " not found");
            return "error";
        }
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new TasksDTO());
        model.addAttribute("projects", tasksService.getAllProjects());
        model.addAttribute("activePage", "tasks");
        return "task-form";
    }

    @GetMapping("/edit/{title}")
    public String showEditForm(@PathVariable String title, Model model) {
        Optional<Tasks> task = tasksService.getTaskByTitle(title);
        if (task.isPresent()) {
            model.addAttribute("task", tasksMapper.toDTO(task.get()));
            model.addAttribute("projects", tasksService.getAllProjects());
            model.addAttribute("activePage", "tasks");
            return "task-form";
        } else {
            model.addAttribute("error", "Task with title " + title + " not found");
            return "error";
        }
    }

    @PostMapping
    public String saveTask(@RequestParam String oldTitle, @ModelAttribute TasksDTO task) {
        tasksService.saveOrUpdateTask(oldTitle, task);
        return "redirect:/tasks";
    }

    @GetMapping("/remove/{title}")
    public String deleteTask(@PathVariable String title) {
        tasksService.deleteTask(title);
        return "redirect:/tasks";
    }
}
