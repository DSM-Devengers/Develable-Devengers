package devengers.develable.develable_server.controller;

import devengers.develable.develable_server.payload.request.EmailAuthRequest;
import devengers.develable.develable_server.service.emailAuth.EmailAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final EmailAuthService emailAuthService;

    @PostMapping("/send")
    public void sendEmail(@RequestParam String email) {
        emailAuthService.sendEmail(email);
    }

    @PostMapping("/resend")
    public void resendEmail(@RequestParam String email) {
        emailAuthService.resendEmail(email);
    }

    @PostMapping("/check")
    public @ResponseBody boolean checkEmail(@RequestBody String key) {
        System.out.println(key);
        return emailAuthService.authenticationEmail(key);
    }
}
