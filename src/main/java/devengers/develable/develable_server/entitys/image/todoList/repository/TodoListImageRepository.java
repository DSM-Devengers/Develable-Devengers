package devengers.develable.develable_server.entitys.image.todoList.repository;

import devengers.develable.develable_server.entitys.image.todoList.TodoListImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoListImageRepository extends CrudRepository<TodoListImage, Integer> {
    List<TodoListImage> findAllByTodoId(Integer todoId);
    Optional<TodoListImage> findByTodoId(Integer todoId);
    Optional<TodoListImage> findByImageName(String imageName);
    void deleteByImageName(String ImageName);
}
