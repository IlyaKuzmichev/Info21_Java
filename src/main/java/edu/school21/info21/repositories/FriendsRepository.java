package edu.school21.info21.repositories;

import edu.school21.info21.model.Friends;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Friends f WHERE (f.peer_1, f.peer_2) IN " +
            "(SELECT f1.peer_1, f1.peer_2 FROM Friends f1 WHERE f1.id = :id " +
            "UNION " +
            "SELECT f1.peer_2, f1.peer_1 FROM Friends f1 WHERE f1.id = :id)")
    void removePairByLogins(Long id);
}
