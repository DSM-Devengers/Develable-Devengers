package devengers.develable.develable_server.payload.request;

import devengers.develable.develable_server.entitys.user.enums.Sex;
import devengers.develable.develable_server.entitys.user.enums.TechnicalFiled;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class SignUpRequest {
    private Integer age;
    private Sex sex;
    private String email;
    private String password;
    private String userName;
    private MultipartFile image;
    private TechnicalFiled technicalFiled;
}
