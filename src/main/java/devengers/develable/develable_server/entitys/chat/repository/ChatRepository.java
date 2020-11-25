package devengers.develable.develable_server.entitys.chat.repository;

import devengers.develable.develable_server.entitys.chat.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Integer> {
}
