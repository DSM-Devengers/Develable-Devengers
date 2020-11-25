package devengers.develable.develable_server.entitys.inServerUser.repository;

import devengers.develable.develable_server.entitys.inServerUser.InServerUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InServerUserRepository extends CrudRepository<InServerUser, Integer> {
}
