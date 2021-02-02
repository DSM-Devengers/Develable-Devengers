package devengers.develable.develable_server.payload.response;

import devengers.develable.develable_server.entitys.user.enums.Sex;
import devengers.develable.develable_server.entitys.user.enums.TechnicalFiled;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FriendResponse {
    private String userCode;
    private TechnicalFiled technicalFiled;
    private String userName;
    private Sex sex;
    private String imageName;
}
