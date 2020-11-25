package devengers.develable.develable_server.entitys.image.user.repository;

import devengers.develable.develable_server.entitys.image.user.UserImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageRepository extends CrudRepository<UserImage, Integer> {
}
