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
}
