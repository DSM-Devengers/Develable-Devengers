package devengers.develable.develable_server.controller;

import devengers.develable.develable_server.payload.response.FriendResponse;
import devengers.develable.develable_server.service.Friend.FriendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendsController {
    private final FriendsService friendsService;

    @GetMapping
    public List<FriendResponse> getMyFriends() {
        return friendsService.getFriends();
    }

    @GetMapping("/image/{imageName}")
    public byte[] getFriendImage(@PathVariable String imageName) {
        return friendsService.getFriendsImage(imageName);
    }

    @PostMapping("/add")
    public void addFriend(@RequestParam String code) {
        friendsService.addFriend(code);
    }

    @DeleteMapping("/{id}")
    public void deleteFriend(@PathVariable Integer id) {
        friendsService.deleteFriend(id);
    }
}
