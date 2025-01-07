package edu.school21.info21.controller;

import edu.school21.info21.dto.PeersDTO;
import edu.school21.info21.model.Peers;
import edu.school21.info21.services.PeersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/peers")
public class PeersController {

    private final PeersService peersService;

    @Autowired
    public PeersController(PeersService peersService) {
        this.peersService = peersService;
    }

    // Главная страница
    @GetMapping
    public String peers(Model model) {
        List<PeersDTO> peersList = peersService.getAllPeers();
        model.addAttribute("peers", peersList);
        return "peers"; // Возвращаем имя шаблона "index.html"
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("peer", new PeersDTO());
        return "peer-form"; // Шаблон для формы добавления/редактирования
    }

    @GetMapping("/edit/{nickname}")
    public String showEditForm(@PathVariable String nickname, Model model) {
        Optional<Peers> peer = peersService.getPeerByNickname(nickname);
        if (peer.isPresent()) {
            model.addAttribute("peer", peer.get());
            return "peer-form"; // Шаблон для формы добавления/редактирования
        } else {
            model.addAttribute("error", "Peer with nickname " + nickname + " not found");
            return "error";
        }
    }

    @PostMapping("/save")
    public String savePeer(@RequestParam String nickname, @RequestParam String birthday) {
        LocalDate birthDate = LocalDate.parse(birthday);
        peersService.createPeer(nickname, birthDate);
        return "redirect:/peers"; // Возвращаемся на страницу списка
    }

    // Страница информации о пользователе
    @GetMapping("/{nickname}")
    public String getPeerByNickname(@PathVariable String nickname, Model model) {
        Optional<Peers> peer = peersService.getPeerByNickname(nickname);
        if (peer.isPresent()) {
            model.addAttribute("peer", peer.get());
            return "peer"; // Возвращаем шаблон peer.html
        } else {
            model.addAttribute("error", "Peer with nickname " + nickname + " not found");
            return "error";
        }
    }
}
