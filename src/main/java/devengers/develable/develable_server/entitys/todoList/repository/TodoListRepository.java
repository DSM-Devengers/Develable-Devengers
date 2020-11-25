package devengers.develable.develable_server.entitys.todoList.repository;

import devengers.develable.develable_server.entitys.todoList.TodoList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends CrudRepository<TodoList, Integer> {
}
