package devengers.develable.develable_server.entitys.user.repository;

import devengers.develable.develable_server.entitys.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
