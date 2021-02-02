package devengers.develable.develable_server.security.auth;

import devengers.develable.develable_server.entitys.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public AuthDetails loadUserByUsername(String receiptCode) throws UsernameNotFoundException {
        return userRepository.findById(Integer.parseInt(receiptCode))
                .map(AuthDetails::new)
                .orElseThrow(RuntimeException::new);
    }

}
