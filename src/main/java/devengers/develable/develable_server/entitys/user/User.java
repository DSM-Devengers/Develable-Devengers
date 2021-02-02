package devengers.develable.develable_server.entitys.user;

import devengers.develable.develable_server.entitys.user.enums.Sex;
import devengers.develable.develable_server.entitys.user.enums.TechnicalFiled;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userCode;

    private Integer age;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    @Column
    @Enumerated(EnumType.STRING)
    private TechnicalFiled technicalFiled;
}
