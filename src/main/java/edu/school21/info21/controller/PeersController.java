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

//    @GetMapping("/{nickname}")
//    public Optional<Peers> getPeerByNickname(@PathVariable String nickname) {
//        return peersService.getPeerByNickname(nickname);
//    }

    @PostMapping
    public Peers createPeer(@RequestParam String nickname, @RequestParam String birthday) {
        LocalDate birthDate = LocalDate.parse(birthday); // Парсим строку в LocalDate
        return peersService.createPeer(nickname, birthDate);
    }

    @PutMapping("/{nickname}")
    public String updatePeer(@PathVariable String nickname, @RequestParam String birthday, Model model) {
        LocalDate newBirthday = LocalDate.parse(birthday);
        Peers updatedPeer = peersService.updatePeer(nickname, newBirthday);
        model.addAttribute("peer", updatedPeer);
        return "peer";
    }

    @DeleteMapping("/{nickname}")
    public void deletePeer(@PathVariable String nickname) {
        peersService.deletePeer(nickname);
    }
}
