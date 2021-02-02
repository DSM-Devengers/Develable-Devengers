package devengers.develable.develable_server.service.todoList;

import devengers.develable.develable_server.entitys.image.todoList.TodoListImage;
import devengers.develable.develable_server.entitys.image.todoList.repository.TodoListImageRepository;
import devengers.develable.develable_server.entitys.todoList.TodoList;
import devengers.develable.develable_server.entitys.todoList.repository.TodoListRepository;
import devengers.develable.develable_server.entitys.user.User;
import devengers.develable.develable_server.entitys.user.repository.UserRepository;
import devengers.develable.develable_server.payload.request.TodoListRequest;
import devengers.develable.develable_server.payload.request.TodoListUpdateRequest;
import devengers.develable.develable_server.payload.response.TodoListResponse;
import devengers.develable.develable_server.payload.response.page.TodoListPage;
import devengers.develable.develable_server.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;
    private final TodoListImageRepository todoListImageRepository;

    private final AuthenticationFacade authenticationFacade;

    @Value("${todo.file.dir}")
    String todoFileDir;

    private <T> void setIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    @SneakyThrows
    @Override
    public void createTodo(TodoListRequest todoListRequest) {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        TodoList todoList = TodoList.builder()
                .title(todoListRequest.getTitle())
                .content(todoListRequest.getContent())
                .userId(user.getUserId())
                .create_at(LocalDate.now())
                .build();
        todoListRepository.save(todoList);

        for (MultipartFile file : todoListRequest.getFiles()) {
            if (!file.isEmpty()) {
                String filename = UUID.randomUUID().toString();

                todoListImageRepository.save(
                        TodoListImage.builder()
                                .imageName(filename)
                                .todoId(todoList.getId())
                                .build()
                );

                file.transferTo(new File(todoFileDir, filename));
            }
        }
    }

    @SneakyThrows
    @Override
    public void addTodo(Integer todoId, MultipartFile[] files) {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String filename = UUID.randomUUID().toString();

                todoListImageRepository.save(
                        TodoListImage.builder()
                                .imageName(filename)
                                .todoId(todoId)
                                .build()
                );

                file.transferTo(new File(todoFileDir, filename));
            }
        }
    }

    @Override
    public TodoListPage getTodoList() {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        int page = 1;

        List<TodoList> todoLists = todoListRepository.findAllByUserId(user.getUserId());
        List<TodoListResponse> todoListResponses = new ArrayList<>();

        for (TodoList todoList : todoLists) {
            List<TodoListImage> todoListImages = todoListImageRepository.findAllByTodoId(todoList.getId());
            List<String> filesNames = new ArrayList<>();

            if (todoListImages.isEmpty()) {
                filesNames.add("deleted");
            } else {
                for (TodoListImage todoListImage : todoListImages) {
                    filesNames.add(todoListImage.getImageName());
                }
            }

            TodoListResponse todoListResponse = TodoListResponse.builder()
                    .title(todoList.getTitle())
                    .content(todoList.getContent())
                    .TodoId(todoList.getId())
                    .create_at(LocalDate.now())
                    .filenames(filesNames)
                    .build();

            todoListResponses.add(todoListResponse);
        }
        return TodoListPage.builder()
                .page(page)
                .todolist(todoListResponses.subList(((page - 1) * 20), (page + 19)))
                .build();
    }

    @Override
    public void updateTodo(TodoListUpdateRequest update) {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);
        TodoList todoList = todoListRepository.findByUserId(user.getUserId())
                .orElseThrow(RuntimeException::new);

        setIfNotNull(todoList::setTitle, update.getTitle());
        setIfNotNull(todoList::setContent, update.getContent());
        setIfNotNull(todoList::setCreate_at, LocalDate.now());

        todoListRepository.save(todoList);
    }

    @SneakyThrows
    @Override
    public void updateTodoFile(String fileName, MultipartFile file) {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);
        TodoListImage todoListImage = todoListImageRepository.findByImageName(fileName)
                .orElseThrow(RuntimeException::new);

        File todoFile = new File(todoFileDir, todoListImage.getImageName());
        if (!todoFile.exists()) todoFile.delete();

        String filename = UUID.randomUUID().toString();
        todoListImage.update(filename);

        file.transferTo(new File(todoFileDir, filename));
    }

    @SneakyThrows
    @Override
    public byte[] getFile(String filename) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);
        todoListImageRepository.findByImageName(filename)
                .orElseThrow(RuntimeException::new);

        File file = new File(todoFileDir, filename);
        if (file.exists()) throw new RuntimeException();

        InputStream inputStream = new FileInputStream(file);

        return IOUtils.toByteArray(inputStream);
    }

    @Override
    public void deleteTodoFile(String filename) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        File file = new File(todoFileDir, filename);
        if (file.exists()) file.delete();

        todoListImageRepository.deleteByImageName(filename);
    }

    @Override
    public void deleteTodo(Integer todoId) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);
        todoListRepository.findById(todoId)
                .orElseThrow(RuntimeException::new);
        TodoListImage todoListImage = todoListImageRepository.findByTodoId(todoId)
                .orElse(
                        TodoListImage.builder()
                                .imageName("deleted")
                                .build()
                );

        if (!todoListImage.equals("deleted")) {
            File file = new File(todoFileDir, todoListImage.getImageName());
            if (file.exists()) file.delete();

            todoListImageRepository.deleteByImageName(todoListImage.getImageName());
        }
        todoListRepository.deleteById(todoId);
    }
}
