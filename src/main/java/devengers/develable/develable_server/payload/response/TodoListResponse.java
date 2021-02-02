package devengers.develable.develable_server.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class TodoListResponse {
    private Integer TodoId;
    private String title;
    private String content;
    private LocalDate create_at;
    private List<String> filenames;
}
