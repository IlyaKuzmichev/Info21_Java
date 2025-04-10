package edu.school21.info21.services;

import edu.school21.info21.model.Friends;
import edu.school21.info21.repositories.FriendsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService {

    public final FriendsRepository friendsRepository;

    public FriendsService(FriendsRepository friendsRepository) {
        this.friendsRepository = friendsRepository;
    }

    public List<Friends> getAllFriends() { return friendsRepository.findAll(); }

    public void deleteFriendship(Long id) {
        friendsRepository.removePairByLogins(id);
    }

    public Friends getFriendsById(Long id) {
        return friendsRepository.findById(id).orElse(null);
    }

    public void saveFriendship(String peer_1, String peer_2) {
        Friends friends = new Friends();
        friends.setPeer_1(peer_1);
        friends.setPeer_2(peer_2);
        friendsRepository.save(friends);
    }
}
