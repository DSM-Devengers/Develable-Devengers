package devengers.develable.develable_server.service.emailAuth;

import devengers.develable.develable_server.entitys.emailAuth.EmailAuth;
import devengers.develable.develable_server.entitys.emailAuth.repository.EmailAuthRepository;
import devengers.develable.develable_server.entitys.user.User;
import devengers.develable.develable_server.entitys.user.repository.UserRepository;
import devengers.develable.develable_server.payload.request.EmailAuthRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailAuthServiceImpl implements EmailAuthService{

    private final EmailAuthRepository emailAuthRepository;
    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;

    private String getKey() {
        StringBuilder key = new StringBuilder();

        for(int i = 0; i < 6; i++) {
            int div = (int)Math.floor(Math.random() * 2);
            if(div == 0) {
                key.append((char) (Math.random() * 10 + '0'));
            }else {
                key.append((char)(Math.random()*26 + 'A'));
            }
        }

        return key.toString();
    }

    private boolean checkSame(String email) {
        List<User> users = userRepository.findAll();

        for(User user : users) {
            if(user.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }

    private void sendMail(String email) {
        if(email.isEmpty()) {
            throw new RuntimeException();
        }
        else if(checkSame(email)) {
            throw new RuntimeException();
        }else {
            String key = getKey();

            EmailAuth emailAuth =
                    EmailAuth.builder()
                            .key(key)
                            .email(email)
                            .build();

            emailAuthRepository.save(emailAuth);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("[Develable]인증을 위한 메일입니다");
            message.setText(
                    "저희 앱을 이용해 주셔서 감사합니다!\n" +
                            "인증코드 : " + emailAuth.getKey()
            );

            javaMailSender.send(message);
        }
    }

    @SneakyThrows
    @Override
    public void sendEmail(String email) {
        sendMail(email);
    }

    @SneakyThrows
    @Override
    public void resendEmail(String email) {
        sendMail(email);
    }

    @Override
    public boolean authenticationEmail(String key) {
        System.out.println(key);
        EmailAuth emailAuth = emailAuthRepository.findByKey(key)
                .orElseThrow(RuntimeException::new);

        if(emailAuth.getKey().isEmpty())
            return false;

        emailAuthRepository.deleteById(emailAuth.getId());
        return true;
    }
}
