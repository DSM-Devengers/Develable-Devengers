package devengers.develable.develable_server.service.user;

import devengers.develable.develable_server.payload.request.ChangeUserRequest;
import devengers.develable.develable_server.payload.request.SignUpRequest;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void signUp(SignUpRequest signUpRequest);
    void changeUserInfo(ChangeUserRequest changeUserRequest);
    void changeUserImage(MultipartFile image);
    void changePassword(String password);
}
