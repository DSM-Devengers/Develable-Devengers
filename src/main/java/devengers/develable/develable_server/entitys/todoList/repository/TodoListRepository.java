package devengers.develable.develable_server.entitys.todoList.repository;

import devengers.develable.develable_server.entitys.todoList.TodoList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoListRepository extends CrudRepository<TodoList, Integer> {
    List<TodoList> findAllByUserId(Integer userId);
    Optional<TodoList> findByUserId(Integer userId);
    void deleteByUserId(Integer userId);
}
