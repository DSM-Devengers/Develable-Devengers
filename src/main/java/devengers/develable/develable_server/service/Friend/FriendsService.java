package devengers.develable.develable_server.service.Friend;

import devengers.develable.develable_server.payload.response.FriendResponse;

import java.util.List;

public interface FriendsService {
    List<FriendResponse> getFriends();
    void deleteFriend(Integer id);
    void addFriend(String code);
    byte[] getFriendsImage(String imageName);
}
