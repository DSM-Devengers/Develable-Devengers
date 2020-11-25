package devengers.develable.develable_server.entitys.chatSercer.repository;

import devengers.develable.develable_server.entitys.chatSercer.ChatServer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatServerRepository extends CrudRepository<ChatServer, Integer> {
}
