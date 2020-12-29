package devengers.develable.develable_server.controller;

import devengers.develable.develable_server.entitys.user.enums.Sex;
import devengers.develable.develable_server.entitys.user.enums.TechnicalFiled;
import devengers.develable.develable_server.payload.request.ChangeUserRequest;
import devengers.develable.develable_server.payload.request.SignUpRequest;
import devengers.develable.develable_server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public void signUp(@RequestParam String userName,
                       @RequestParam String email,
                       @RequestParam String password,
                       @RequestParam Sex sex,
                       @RequestParam TechnicalFiled technicalFiled,
                       @RequestParam Integer age,
                       @RequestParam MultipartFile image) {

        userService.signUp(
                SignUpRequest.builder()
                .userName(userName)
                .age(age)
                .email(email)
                .password(password)
                .image(image)
                .sex(sex)
                .technicalFiled(technicalFiled)
                .build()
        );
    }

    @PutMapping("/changeUser")
    public void changeUserInfo(@RequestBody ChangeUserRequest changeUserRequest) {
        userService.changeUserInfo(changeUserRequest);
    }

    @PutMapping("/changeUserImage")
    public void changeUserImage(@RequestParam MultipartFile image) {
        userService.changeUserImage(image);
    }

    @PutMapping("/changeUserPassword")
    public void changePassword(@RequestBody String password) {
        userService.changePassword(password);
    }
}
