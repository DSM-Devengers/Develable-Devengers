package devengers.develable.develable_server.service.auth;

import devengers.develable.develable_server.payload.request.SignInRequest;
import devengers.develable.develable_server.payload.response.TokenResponse;

public interface AuthService {
    TokenResponse signIn(SignInRequest signInRequest);
    TokenResponse refreshToken(String refreshToken);
}
