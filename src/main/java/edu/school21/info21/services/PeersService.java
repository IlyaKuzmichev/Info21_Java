package edu.school21.info21.services;

import edu.school21.info21.model.Peers;
import edu.school21.info21.repositories.PeersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PeersService {

    private PeersRepository peersRepository;

    @Autowired
    public PeersService(PeersRepository peersRepository) {
        this.peersRepository = peersRepository;
    }

    public List<Peers> getAllPeers() {
        return peersRepository.findAll();
    }

    public Peers createPeer(String nickname, LocalDate birthday) {
        Peers peer = new Peers(nickname, birthday); // Сохраняем дату как строку
        return peersRepository.save(peer);
    }

    public Optional<Peers> getPeerByNickname(String nickname) {
        return peersRepository.findById(nickname);
    }

    public Peers updatePeer(String nickname, LocalDate newBirthday) {
        Optional<Peers> existingPeer = peersRepository.findById(nickname);
        if (existingPeer.isPresent()) {
            Peers peer = existingPeer.get();
            peer.setBirthday(newBirthday); // Обновляем дату рождения
            return peersRepository.save(peer);
        } else {
            throw new IllegalArgumentException("Peer with nickname " + nickname + " not found");
        }
    }

    public void deletePeer(String nickname) {
        peersRepository.deleteById(nickname);
    }
}
