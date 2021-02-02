package devengers.develable.develable_server.payload.response;

import devengers.develable.develable_server.entitys.userLanguge.enums.UserLanguageEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLanguagesResponse {
    private UserLanguageEnum userLanguageEnum;
}
