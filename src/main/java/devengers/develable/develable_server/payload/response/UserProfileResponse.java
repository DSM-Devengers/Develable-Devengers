package devengers.develable.develable_server.payload.response;

import com.sun.org.apache.xpath.internal.operations.Bool;
import devengers.develable.develable_server.entitys.user.enums.TechnicalFiled;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserProfileResponse {
    private String userCode;
    private String userName;
    private String email;
    private TechnicalFiled technicalFiled;
    private Integer age;
    private List<UserLanguagesResponse> userLanguages;
    private String imageName;
    private boolean isMine;
}
