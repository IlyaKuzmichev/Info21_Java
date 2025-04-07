package edu.school21.info21.controller;

import edu.school21.info21.dto.PeersDTO;
import edu.school21.info21.model.Peers;
import edu.school21.info21.services.PeersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/peers")
public class PeersController {

    private final PeersService peersService;

    @Autowired
    public PeersController(PeersService peersService) {
        this.peersService = peersService;
    }

    @GetMapping
    public String peers(Model model) {
        log.info("Пользователь зашел на страницу списка пиров");
        List<PeersDTO> peersList = peersService.getAllPeers();
        model.addAttribute("peers", peersList);
        model.addAttribute("activePage", "peers");
        return "peers";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        log.info("Пользователь зашел на страницу добавления пира");
        model.addAttribute("peer", new PeersDTO());
        model.addAttribute("activePage", "peers");
        return "peer-form";
    }

    @GetMapping("/edit/{nickname}")
    public String showEditForm(@PathVariable String nickname, Model model) {
        log.info("Пользователь зашел на страницу редактирования пира {}", nickname);
        Optional<Peers> peer = peersService.getPeerByNickname(nickname);
        if (peer.isPresent()) {
            model.addAttribute("peer", peer.get());
            model.addAttribute("activePage", "peers");
            return "peer-form";
        } else {
            log.error("Пир {} не найден", nickname);
            model.addAttribute("error", "Peer with nickname " + nickname + " not found");
            return "error";
        }
    }

    @PostMapping("/save")
    public String savePeer(@RequestParam String nickname, @RequestParam String birthday) {
        LocalDate birthDate = LocalDate.parse(birthday);
        if (peersService.getPeerByNickname(nickname).isPresent()) {
            log.info("Обновление пира {} с датой {}", nickname, birthday);
            peersService.updatePeer(nickname, birthDate);
        } else {
            log.info("Создание пира {} с датой {}", nickname, birthday);
            peersService.createPeer(nickname, birthDate);
        }
        return "redirect:/peers";
    }

    @GetMapping("/remove/{nickname}")
    public String deletePeer(@PathVariable String nickname) {
        log.warn("Удаление пира {}", nickname);
        peersService.deletePeer(nickname);
        return "redirect:/peers";
    }

    @GetMapping("/{nickname}")
    public String getPeerByNickname(@PathVariable String nickname, Model model) {
        log.info("Пользователь открыл страницу пира {}", nickname);
        Optional<Peers> peer = peersService.getPeerByNickname(nickname);
        if (peer.isPresent()) {
            model.addAttribute("peer", peer.get());
            model.addAttribute("activePage", "peers");
            return "peer";
        } else {
            log.error("Пир {} не найден", nickname);
            model.addAttribute("error", "Peer with nickname " + nickname + " not found");
            return "error";
        }
    }
}

