package devengers.develable.develable_server.entitys.userLanguge.repository;

import devengers.develable.develable_server.entitys.userLanguge.UserLanguage;
import devengers.develable.develable_server.entitys.userLanguge.enums.UserLanguageEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLanguageRepository extends CrudRepository<UserLanguage, Integer> {
    void deleteByUserLanguageEnum(UserLanguageEnum userLanguageEnum);
    void deleteByUserId(Integer userId);
    List<UserLanguage> findAllByUserId(Integer userId);
}
