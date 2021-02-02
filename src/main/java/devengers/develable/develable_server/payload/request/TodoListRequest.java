package devengers.develable.develable_server.payload.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class TodoListRequest {
    private String title;
    private String content;
    private MultipartFile[] files;
}
