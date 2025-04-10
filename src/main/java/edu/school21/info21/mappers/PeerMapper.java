package edu.school21.info21.mappers;

import edu.school21.info21.dto.PeersDTO;
import edu.school21.info21.model.Peers;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PeerMapper {
    public PeersDTO toDTO(Peers peer) {
        return new PeersDTO(peer.getNickname(), peer.getBirthday().toString());
    }

    public Peers toEntity(PeersDTO dto) {
        return new Peers(dto.getNickname(), LocalDate.parse(dto.getBirthday()));
    }
}
