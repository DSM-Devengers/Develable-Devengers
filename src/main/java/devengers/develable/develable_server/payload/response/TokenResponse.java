package devengers.develable.develable_server.payload.response;

import lombok.Getter;

@Getter
public class TokenResponse {
    public TokenResponse(String accessToken, String refreshToken, String tokenType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
    }

    private String accessToken;

    private String refreshToken;

    private String tokenType;
}
