package devengers.develable.develable_server.service.userLanguage;

import devengers.develable.develable_server.payload.request.UserLanguageRequest;

import java.util.List;

public interface UserLanguageService {
    void setUserLanguages(List<UserLanguageRequest> userLanguages);
    void deleteUserLanguage(List<UserLanguageRequest> userLanguageDelete);
}
