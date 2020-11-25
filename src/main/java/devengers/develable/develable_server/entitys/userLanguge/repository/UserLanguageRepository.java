package devengers.develable.develable_server.entitys.userLanguge.repository;

import devengers.develable.develable_server.entitys.userLanguge.UserLanguage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLanguageRepository extends CrudRepository<UserLanguage, Integer> {

}
