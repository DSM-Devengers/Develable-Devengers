package devengers.develable.develable_server.service.Friend;

import devengers.develable.develable_server.entitys.friends.Friends;
import devengers.develable.develable_server.entitys.friends.repository.FriendsRepository;
import devengers.develable.develable_server.entitys.image.user.UserImage;
import devengers.develable.develable_server.entitys.image.user.repository.UserImageRepository;
import devengers.develable.develable_server.entitys.user.User;
import devengers.develable.develable_server.entitys.user.repository.UserRepository;
import devengers.develable.develable_server.payload.response.FriendResponse;
import devengers.develable.develable_server.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendsServiceImpl implements FriendsService{
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;

    private final AuthenticationFacade authenticationFacade;

    @Value("${user.image.dir}")
    private String userImageDir;

    @Override
    public List<FriendResponse> getFriends() {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        List<Friends> friends = friendsRepository.findAllByUserId(user.getUserId());
        List<FriendResponse> myFriends = new ArrayList<>();

        for(Friends friend : friends) {
            User friend1 = userRepository.findByUserCode(friend.getFriendCode())
                    .orElseThrow(RuntimeException::new);

            UserImage userImage = userImageRepository.findByUserId(user.getUserId())
                    .orElseThrow(RuntimeException::new);

            FriendResponse friendResponse =
                    FriendResponse.builder()
                    .userCode(friend1.getUserCode())
                    .userName(friend1.getUserName())
                    .sex(friend1.getSex())
                    .technicalFiled(friend1.getTechnicalFiled())
                    .imageName(userImage.getImageName())
                    .build();

            myFriends.add(friendResponse);
        }

        return myFriends;
    }

    @Override
    public void addFriend(String code) {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        friendsRepository.save(
                Friends.builder()
                .userId(user.getUserId())
                .friendCode(code)
                .build()
        );
    }

    @Override
    public void deleteFriend(Integer id) {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        friendsRepository.deleteById(id);
    }

    @SneakyThrows
    @Override
    public byte[] getFriendsImage(String imageName) {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        File file = new File(userImageDir, imageName);
        if(!file.exists()) throw new RuntimeException();

        InputStream input = new FileInputStream(file);

        return IOUtils.toByteArray(input);
    }
}
