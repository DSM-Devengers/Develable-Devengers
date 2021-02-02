package devengers.develable.develable_server.service.refreshToken;

import devengers.develable.develable_server.entitys.refresh_token.RefreshToken;
import devengers.develable.develable_server.entitys.refresh_token.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService{

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }
}
