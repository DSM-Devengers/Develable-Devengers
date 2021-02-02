package devengers.develable.develable_server.service.todoList;

import devengers.develable.develable_server.payload.request.TodoListRequest;
import devengers.develable.develable_server.payload.request.TodoListUpdateRequest;
import devengers.develable.develable_server.payload.response.page.TodoListPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TodoListService {
    void createTodo(TodoListRequest todoListRequest);
    void addTodo(Integer todoId, MultipartFile[] files);
    void updateTodo(TodoListUpdateRequest update);
    void updateTodoFile(String fileName, MultipartFile file);
    TodoListPage getTodoList();
    byte[] getFile(String filename);
    void deleteTodo(Integer todoId);
    void deleteTodoFile(String filename);
}
