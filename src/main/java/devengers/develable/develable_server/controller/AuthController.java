package devengers.develable.develable_server.controller;

import devengers.develable.develable_server.payload.request.SignInRequest;
import devengers.develable.develable_server.payload.response.TokenResponse;
import devengers.develable.develable_server.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public TokenResponse signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @PutMapping
    public TokenResponse refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return authService.refreshToken(refreshToken);
    }

}
