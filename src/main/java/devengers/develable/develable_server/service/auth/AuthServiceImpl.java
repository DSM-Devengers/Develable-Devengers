package devengers.develable.develable_server.service.auth;

import devengers.develable.develable_server.entitys.refresh_token.RefreshToken;
import devengers.develable.develable_server.entitys.refresh_token.repository.RefreshTokenRepository;
import devengers.develable.develable_server.entitys.user.User;
import devengers.develable.develable_server.entitys.user.repository.UserRepository;
import devengers.develable.develable_server.payload.request.SignInRequest;
import devengers.develable.develable_server.payload.response.TokenResponse;
import devengers.develable.develable_server.security.JwtProvider;
import devengers.develable.develable_server.service.refreshToken.RefreshTokenServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private Long refreshExp;

    private String tokenType;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenServiceImpl refreshTokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponse signIn(SignInRequest signInRequest) {
        return userRepository.findByEmail(signInRequest.getEmail())
                .filter(user -> passwordEncoder.matches(signInRequest.getPassword(), user.getPassword()))
                .map(User::getId)
                .map(id -> {
                    String accessToken = jwtProvider.generateAccessToken(id);
                    String refreshToken = jwtProvider.generateRefreshToken(id);
                    refreshTokenService.save(new RefreshToken(id, refreshToken, refreshExp));
                    return new TokenResponse(accessToken, refreshToken, tokenType);
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public TokenResponse refreshToken(String receivedToken) {
        if(!jwtProvider.isRefreshToken(receivedToken))
            throw new RuntimeException();

        return refreshTokenRepository.findByRefreshToken(receivedToken)
                .map(refreshToken -> {
                    String generatedRefreshToken = jwtProvider.generateRefreshToken(refreshToken.getId());
                    return refreshToken.update(generatedRefreshToken, refreshExp);
                })
                .map(refreshTokenRepository::save)
                .map(refreshToken -> {
                    String generatedAccessToken = jwtProvider.generateAccessToken(refreshToken.getId());
                    return new TokenResponse(generatedAccessToken, refreshToken.getRefreshToken(), tokenType);
                })
                .orElseThrow(RuntimeException::new);
    }
}
