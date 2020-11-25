package devengers.develable.develable_server.entitys.chatAuthority.repository;

import devengers.develable.develable_server.entitys.chatAuthority.ChatAuthorities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatAuthoritiesRepository extends CrudRepository<ChatAuthorities, Integer> {
}
