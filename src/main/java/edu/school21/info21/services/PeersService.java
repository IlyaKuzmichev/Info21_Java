package edu.school21.info21.services;

import edu.school21.info21.dto.PeersDTO;
import edu.school21.info21.mappers.PeerMapper;
import edu.school21.info21.model.Peers;
import edu.school21.info21.repositories.PeersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeersService {

    private final PeersRepository peersRepository;
    private final PeerMapper peerMapper;

    @Autowired
    public PeersService(PeersRepository peersRepository, PeerMapper peerMapper) {
        this.peersRepository = peersRepository;
        this.peerMapper = peerMapper;
    }

    public List<PeersDTO> getAllPeers() {
        List<Peers> peersList = peersRepository.findAll();
        List<PeersDTO> peersDTOList = new ArrayList<>();
        for (Peers peer : peersList) {
            peersDTOList.add(peerMapper.toDTO(peer));
        }
        return peersDTOList;
    }

    public Peers createPeer(String nickname, LocalDate birthday) {
        Peers peer = new Peers(nickname, birthday);
        return peersRepository.save(peer);
    }

    public Peers updatePeer(String nickname, LocalDate newBirthday) {
        Peers peer = peersRepository.findById(nickname)
                .orElseThrow(() -> new IllegalArgumentException("Peer " + nickname + " not found"));
        peer.setBirthday(newBirthday);
        return peersRepository.save(peer);
    }

    public Optional<Peers> getPeerByNickname(String nickname) {
        return peersRepository.findById(nickname);
    }

    public void deletePeer(String nickname) {
        peersRepository.deleteById(nickname);
    }
}

