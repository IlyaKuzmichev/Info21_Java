package edu.school21.info21.controller;

import edu.school21.info21.model.Friends;
import edu.school21.info21.services.FriendsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/friends")
public class FriendsController {

    private final FriendsService friendsService;

    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @GetMapping
    public String getAllFriends(Model model) {
        List<Friends> friendsList = friendsService.getAllFriends();
        model.addAttribute("friends", friendsList);
        model.addAttribute("activePage", "friends");
        return "friends";
    }
}
