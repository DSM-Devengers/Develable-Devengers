package devengers.develable.develable_server.controller;

import devengers.develable.develable_server.payload.response.UserProfileResponse;
import devengers.develable.develable_server.service.userProfile.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public UserProfileResponse getProfile(@RequestHeader("X-UserCode") @Nullable String userCode) {
        return userProfileService.getUserProfile(userCode);
    }

    @GetMapping("/image/{imageName}")
    public byte[] getUserImage(@PathVariable String imageName) {
        return userProfileService.getUserImage(imageName);
    }
}
