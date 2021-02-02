package devengers.develable.develable_server.service.userLanguage;

import devengers.develable.develable_server.entitys.user.User;
import devengers.develable.develable_server.entitys.user.repository.UserRepository;
import devengers.develable.develable_server.entitys.userLanguge.UserLanguage;
import devengers.develable.develable_server.entitys.userLanguge.repository.UserLanguageRepository;
import devengers.develable.develable_server.payload.request.UserLanguageRequest;
import devengers.develable.develable_server.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLanguageServiceImpl implements UserLanguageService{

    private final UserRepository userRepository;
    private final UserLanguageRepository userLanguageRepository;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public void setUserLanguages(List<UserLanguageRequest> userLanguages) {
        User user = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        for(UserLanguageRequest userLanguageRequest : userLanguages) {
            userLanguageRepository.save(
                    UserLanguage.builder()
                            .userLanguageEnum(userLanguageRequest.getUserLanguageEnum())
                            .userId(user.getUserId())
                            .build()
            );
        }
    }

    @Override
    public void deleteUserLanguage(List<UserLanguageRequest> userLanguageDelete) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        for(UserLanguageRequest userLanguage : userLanguageDelete) {
            userLanguageRepository.deleteByUserLanguageEnum(
                    userLanguage.getUserLanguageEnum()
            );
        }
    }
}
