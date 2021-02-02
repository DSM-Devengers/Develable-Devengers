package devengers.develable.develable_server.entitys.refresh_token.repository;

import devengers.develable.develable_server.entitys.refresh_token.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByRefreshToken(String RefreshToken);
}
