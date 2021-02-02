package devengers.develable.develable_server.service.emailAuth;

import devengers.develable.develable_server.payload.request.EmailAuthRequest;

public interface EmailAuthService {
    void sendEmail(String email);
    boolean authenticationEmail(String key);
    void resendEmail(String email);
}
