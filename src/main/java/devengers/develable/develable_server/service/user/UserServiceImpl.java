package devengers.develable.develable_server.service.user;

import devengers.develable.develable_server.entitys.image.user.UserImage;
import devengers.develable.develable_server.entitys.image.user.repository.UserImageRepository;
import devengers.develable.develable_server.entitys.user.User;
import devengers.develable.develable_server.entitys.user.repository.UserRepository;
import devengers.develable.develable_server.payload.request.ChangeUserRequest;
import devengers.develable.develable_server.payload.request.SignUpRequest;
import devengers.develable.develable_server.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationFacade authenticationFacade;

    @Value("${user.image.dir}")
    private String imageDir;

    private <T> void setIfNotNull(Consumer<T> setter, T value) {
        if(value != null) {
            setter.accept(value);
        }
    }

    @SneakyThrows
    @Override
    public void signUp(SignUpRequest signUpRequest) {
        User user = userRepository.save(
                User.builder()
                .userName(signUpRequest.getUserName())
                .age(signUpRequest.getAge())
                .email(signUpRequest.getEmail())
                .sex(signUpRequest.getSex())
                .technicalFiled(signUpRequest.getTechnicalFiled())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build()
        );

        UserImage userImage = userImageRepository.save(
                UserImage.builder()
                .userId(user.getUserId())
                .imageName(signUpRequest.getImage().isEmpty() ? "null" : UUID.randomUUID().toString())
                .build()
        );
        if(!signUpRequest.getImage().isEmpty())
            signUpRequest.getImage()
                .transferTo(new File(imageDir, userImage.getImageName()));
    }



    @Override
    public void changeUserInfo(ChangeUserRequest changeUserRequest) {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        setIfNotNull(user::setAge, changeUserRequest.getAge());
        setIfNotNull(user::setUserName, changeUserRequest.getUserName());
        setIfNotNull(user::setSex, changeUserRequest.getSex());
        setIfNotNull(user::setTechnicalFiled, changeUserRequest.getTechnicalFiled());

        userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public void changeUserImage(MultipartFile image) {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        UserImage userImage = userImageRepository.findByUserId(user.getId())
                .orElseThrow(RuntimeException::new);

        String imageName = UUID.randomUUID().toString();

        File file = new File(imageDir, userImage.getImageName());
        if(file.exists()) file.delete();

        userImage.update(image.isEmpty() ? "null" : imageName);

        image.transferTo(new File(imageDir, imageName));
    }

    @Override
    public void changePassword(String password) {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        setIfNotNull(user::setPassword, passwordEncoder.encode(password));
    }
}
