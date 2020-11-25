package devengers.develable.develable_server.entitys.image.todoList.repository;

import devengers.develable.develable_server.entitys.image.todoList.TodoListImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListImageRepository extends CrudRepository<TodoListImage, Integer> {
}
