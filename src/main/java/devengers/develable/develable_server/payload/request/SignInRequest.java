package devengers.develable.develable_server.payload.request;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String email;
    private String password;
}
