package devengers.develable.develable_server.entitys.chatServerAuthority.repository;

import devengers.develable.develable_server.entitys.chatServerAuthority.ChatServerAuthorities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatServerAuthoritiesRepository extends CrudRepository<ChatServerAuthorities, Integer> {
}
