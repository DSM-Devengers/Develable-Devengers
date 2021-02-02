package devengers.develable.develable_server.entitys.friends.repository;

import devengers.develable.develable_server.entitys.friends.Friends;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendsRepository extends CrudRepository<Friends, Integer> {
    List<Friends> findAllByUserId(Integer id);
    void deleteById(Integer id);
}
