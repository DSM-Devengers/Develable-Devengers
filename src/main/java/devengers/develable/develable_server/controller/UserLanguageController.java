package devengers.develable.develable_server.controller;

import devengers.develable.develable_server.payload.request.UserLanguageRequest;
import devengers.develable.develable_server.service.userLanguage.UserLanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userLanguage")
@RequiredArgsConstructor
public class UserLanguageController {
    private final UserLanguageService userLanguageService;

    @PostMapping
    public void setUserLanguages(@RequestBody List<UserLanguageRequest> userLanguages) {
        userLanguageService.setUserLanguages(userLanguages);
    }

    @DeleteMapping
    public void deleteUserLanguage(@RequestBody List<UserLanguageRequest> userLanguages) {
        userLanguageService.deleteUserLanguage(userLanguages);
    }
}
