package devengers.develable.develable_server.entitys.image.codeReview.repository;

import devengers.develable.develable_server.entitys.image.codeReview.CodeReviewImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeReviewImageRepository extends CrudRepository<CodeReviewImage, Integer> {
}
