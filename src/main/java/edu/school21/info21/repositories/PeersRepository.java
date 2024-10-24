package edu.school21.info21.repositories;

import edu.school21.info21.model.Peers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeersRepository extends JpaRepository<Peers, String> {
}
