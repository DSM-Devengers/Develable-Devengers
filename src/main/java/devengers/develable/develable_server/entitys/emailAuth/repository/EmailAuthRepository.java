package devengers.develable.develable_server.entitys.emailAuth.repository;

import devengers.develable.develable_server.entitys.emailAuth.EmailAuth;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmailAuthRepository extends CrudRepository<EmailAuth, Integer> {
    Optional<EmailAuth> findByKey(String key);
    Optional<EmailAuth> findByEmail(String email);
    void deleteById(Integer id);
}
