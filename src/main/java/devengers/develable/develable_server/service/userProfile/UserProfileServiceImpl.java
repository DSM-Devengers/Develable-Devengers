package devengers.develable.develable_server.service.userProfile;

import devengers.develable.develable_server.entitys.image.user.UserImage;
import devengers.develable.develable_server.entitys.image.user.repository.UserImageRepository;
import devengers.develable.develable_server.entitys.user.User;
import devengers.develable.develable_server.entitys.user.repository.UserRepository;
import devengers.develable.develable_server.entitys.userLanguge.UserLanguage;
import devengers.develable.develable_server.entitys.userLanguge.repository.UserLanguageRepository;
import devengers.develable.develable_server.payload.response.UserLanguagesResponse;
import devengers.develable.develable_server.payload.response.UserProfileResponse;
import devengers.develable.develable_server.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService{

    private final UserRepository userRepository;
    private final UserLanguageRepository userLanguageRepository;
    private final UserImageRepository userImageRepository;

    private final AuthenticationFacade authenticationFacade;

   @Value("${user.image.dir}")
    private String userImageDir;

    @Override
    public UserProfileResponse getUserProfile(String userCode) {
        User mine = userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        List<UserLanguagesResponse > userLanguagesResponses = new ArrayList<>();

        if(!userCode.isEmpty()) {
            User user = userRepository.findByUserCode(userCode)
                    .orElseThrow(RuntimeException::new);

            UserImage userImage = userImageRepository.findByUserId(user.getUserId())
                    .orElseThrow(RuntimeException::new);

            List<UserLanguage> userLanguages = userLanguageRepository.findAllByUserId(user.getUserId());
            for(UserLanguage userLanguage : userLanguages) {
                UserLanguagesResponse userLanguagesResponse =
                        UserLanguagesResponse.builder()
                                .userLanguageEnum(userLanguage.getUserLanguageEnum())
                                .build();

                userLanguagesResponses.add(userLanguagesResponse);
            }

            return UserProfileResponse.builder()
                    .userCode(user.getUserCode())
                    .userName(user.getUserName())
                    .age(user.getAge())
                    .email(user.getEmail())
                    .technicalFiled(user.getTechnicalFiled())
                    .userLanguages(userLanguagesResponses)
                    .imageName(userImage.getImageName().equals("null") ? "null" : userImage.getImageName())
                    .isMine(mine.getUserCode().equals(user.getUserCode()))
                    .build();
        }else {
            UserImage myImage = userImageRepository.findByUserId(mine.getUserId())
                    .orElseThrow(RuntimeException::new);

            List<UserLanguage> myLanguages = userLanguageRepository.findAllByUserId(mine.getUserId());
            for(UserLanguage userLanguage : myLanguages) {
                UserLanguagesResponse userLanguagesResponse =
                        UserLanguagesResponse.builder()
                                .userLanguageEnum(userLanguage.getUserLanguageEnum())
                                .build();

                userLanguagesResponses.add(userLanguagesResponse);
            }

            return UserProfileResponse.builder()
                    .userCode(mine.getUserCode())
                    .userName(mine.getUserName())
                    .age(mine.getAge())
                    .email(mine.getEmail())
                    .technicalFiled(mine.getTechnicalFiled())
                    .userLanguages(userLanguagesResponses)
                    .imageName(myImage.getImageName())
                    .isMine(true)
                    .build();
        }
    }

    @SneakyThrows
    @Override
    public byte[] getUserImage(String imageName) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(RuntimeException::new);

        File file = new File(userImageDir, imageName);
        if(!file.exists()) throw new RuntimeException();

        InputStream input = new FileInputStream(file);

        return IOUtils.toByteArray(input);
    }
}
