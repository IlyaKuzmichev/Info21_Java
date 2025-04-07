package edu.school21.info21.controller;

import edu.school21.info21.dto.PeersDTO;
import edu.school21.info21.model.Friends;
import edu.school21.info21.services.FriendsService;
import edu.school21.info21.services.PeersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/friends")
public class FriendsController {

    private final FriendsService friendsService;
    private final PeersService peersService;

    public FriendsController(FriendsService friendsService, PeersService peersService) {
        this.friendsService = friendsService;
        this.peersService = peersService;
    }

    @GetMapping
    public String getAllFriends(Model model) {
        log.info("Пользователь зашел на страницу списка друзей");
        List<Friends> friendsList = friendsService.getAllFriends();
        model.addAttribute("friends", friendsList);
        model.addAttribute("activePage", "friends");
        return "friends";
    }

    @GetMapping("/remove/{id}")
    public String deleteFriendship(@PathVariable Long id) {
        log.warn("Пользователь удаляет дружбу {}", id);
        friendsService.deleteFriendship(id);
        return "redirect:/friends";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        log.info("Пользователь зашел на страницу добавления дружбы");
        List<PeersDTO> peersList = peersService.getAllPeers();
        model.addAttribute("peers", peersList);
        model.addAttribute("friends", new Friends());
        model.addAttribute("activePage", "friends");
        return "friends-form"; // Шаблон для формы добавления/редактирования
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        log.info("Пользователь зашел на страницу редактирования дружбы {}", id);
        Friends friends = friendsService.getFriendsById(id);
        List<PeersDTO> peersList = peersService.getAllPeers();
        model.addAttribute("peers", peersList);
        model.addAttribute("friends", friends);
        model.addAttribute("activePage", "friends");
        return "friends-form"; // Шаблон для формы добавления/редактирования
    }

    @PostMapping
    public String saveFriendship(@RequestParam String peer_1, @RequestParam String peer_2) {
        log.info("Пользователь добавляет дружбу {} и {}", peer_1, peer_2);
        friendsService.saveFriendship(peer_1, peer_2);
        return "redirect:/friends";
    }
}
