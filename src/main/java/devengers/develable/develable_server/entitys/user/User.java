package devengers.develable.develable_server.entitys.user;

import devengers.develable.develable_server.entitys.user.enums.Sex;
import devengers.develable.develable_server.entitys.user.enums.TechnicalFiled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer age;
    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String email;

    private String password;

    private String userName;
    @Column
    @Enumerated(EnumType.STRING)
    private TechnicalFiled technicalFiled;
}
