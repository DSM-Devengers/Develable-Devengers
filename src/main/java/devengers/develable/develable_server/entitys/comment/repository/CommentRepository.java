package devengers.develable.develable_server.entitys.comment.repository;

import devengers.develable.develable_server.entitys.comment.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
