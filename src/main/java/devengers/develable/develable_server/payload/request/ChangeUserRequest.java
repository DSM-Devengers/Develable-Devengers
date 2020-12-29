package devengers.develable.develable_server.payload.request;

import devengers.develable.develable_server.entitys.user.enums.Sex;
import devengers.develable.develable_server.entitys.user.enums.TechnicalFiled;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ChangeUserRequest {
    private Integer age;
    private Sex sex;
    private String userName;
    private MultipartFile image;
    private TechnicalFiled technicalFiled;
}
