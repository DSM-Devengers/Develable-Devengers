package devengers.develable.develable_server.entitys.user.repository;

import devengers.develable.develable_server.entitys.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String Email);
    Optional<User> findByUserId(Integer userId);
    Optional<User> findByUserCode(String userCode);
    List<User> findAllByUserId(Integer userId);
    List<User> findAll();
}
