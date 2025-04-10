package edu.school21.info21.controller;

import edu.school21.info21.dto.ChecksDTO;
import edu.school21.info21.services.ChecksService;
import edu.school21.info21.services.PeersService;
import edu.school21.info21.services.TasksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/checks")
public class ChecksController {

    private final ChecksService checksService;
    private final PeersService peersService;
    private final TasksService tasksService;

    @Autowired
    public ChecksController(ChecksService checksService, PeersService peersService, TasksService tasksService) {
        this.checksService = checksService;
        this.peersService = peersService;
        this.tasksService = tasksService;
    }

    @GetMapping
    public String getAllChecks(Model model) {
        log.info("Пользователь открыл список проверок");
        model.addAttribute("checks", checksService.getAllChecks());
        model.addAttribute("activePage", "checks");
        return "checks";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        log.info("Пользователь открыл форму добавления проверки");
        model.addAttribute("check", new ChecksDTO());
        model.addAttribute("peers", peersService.getAllPeers());
        model.addAttribute("tasks", tasksService.getAllTasks());
        model.addAttribute("activePage", "checks");
        return "checks-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        log.info("Пользователь открыл форму редактирования проверки {}", id);
        model.addAttribute("check", checksService.getCheckById(id));
        model.addAttribute("peers", peersService.getAllPeers());
        model.addAttribute("tasks", tasksService.getAllTasks());
        model.addAttribute("activePage", "checks");
        return "checks-form";
    }

    @PostMapping
    public String saveCheck(@RequestParam(required = false) Long id,
                            @RequestParam String peer,
                            @RequestParam String task,
                            @RequestParam String date) {
        if (id == null) {
            log.info("Создание новой проверки: peer={}, task={}, date={}", peer, task, date);
            checksService.saveCheck(peer, task, date);
        } else {
            log.info("Обновление проверки id={}: peer={}, task={}, date={}", id, peer, task, date);
            checksService.updateCheck(id, peer, task, date);
        }
        return "redirect:/checks";
    }

    @GetMapping("/remove/{id}")
    public String deleteCheck(@PathVariable Long id) {
        log.warn("Пользователь удаляет проверку с id={}", id);
        checksService.deleteCheck(id);
        return "redirect:/checks";
    }

}
