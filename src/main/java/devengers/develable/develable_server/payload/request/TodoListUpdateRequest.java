package devengers.develable.develable_server.payload.request;

import lombok.Getter;

@Getter
public class TodoListUpdateRequest {
    private String title;
    private String content;
}
