package devengers.develable.develable_server.service.userProfile;

import devengers.develable.develable_server.payload.response.UserProfileResponse;

import java.util.List;

public interface UserProfileService {
    UserProfileResponse getUserProfile(String userCode);
    byte[] getUserImage(String imageName);

}
