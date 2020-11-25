package devengers.develable.develable_server.entitys.codeReview.repository;

import devengers.develable.develable_server.entitys.codeReview.CodeReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeReviewRepository extends CrudRepository<CodeReview, Integer> {
}
