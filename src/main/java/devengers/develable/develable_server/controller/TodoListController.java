package devengers.develable.develable_server.controller;

import devengers.develable.develable_server.payload.request.TodoListRequest;
import devengers.develable.develable_server.payload.request.TodoListUpdateRequest;
import devengers.develable.develable_server.payload.response.page.TodoListPage;
import devengers.develable.develable_server.service.todoList.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoListController {

    private final TodoListService todoListService;

    @GetMapping
    public TodoListPage getTodoList() {
        return todoListService.getTodoList();
    }

    @GetMapping("/image/{filename}")
    public byte[] getFile(@PathVariable String filename) {
        return todoListService.getFile(filename);
    }

    @PostMapping
    public void createTodoList(@RequestParam String title,
                               @RequestParam String content,
                               @RequestParam MultipartFile[] files) {

        todoListService.createTodo(
                TodoListRequest.builder()
                .title(title)
                .content(content)
                .files(files)
                .build()
        );
    }

    @PostMapping("/image/{todoId}")
    public void addImages(@PathVariable Integer todoId,
                          @RequestParam MultipartFile[] files) {
        todoListService.addTodo(todoId, files);
    }

    @PutMapping
    public void updateTodo(@RequestBody TodoListUpdateRequest update) {
        todoListService.updateTodo(update);
    }

    @PutMapping("/image/{filename}")
    public void updateTodoFile(@PathVariable String filename,
                               @RequestParam MultipartFile file) {
        todoListService.updateTodoFile(filename, file);
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable Integer todoId) {
        todoListService.deleteTodo(todoId);
    }

    @DeleteMapping("/image/{filename}")
    public void deleteTodoFile(@PathVariable String filename) {
        todoListService.deleteTodoFile(filename);
    }
}
