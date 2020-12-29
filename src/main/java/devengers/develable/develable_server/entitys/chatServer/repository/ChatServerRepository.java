package devengers.develable.develable_server.entitys.chatServer.repository;

import devengers.develable.develable_server.entitys.chatServer.ChatServer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatServerRepository extends CrudRepository<ChatServer, Integer> {
}
