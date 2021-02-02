package devengers.develable.develable_server.payload.response.page;

import devengers.develable.develable_server.payload.response.TodoListResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TodoListPage {
    private List<TodoListResponse> todolist;
    private Integer page;
}
