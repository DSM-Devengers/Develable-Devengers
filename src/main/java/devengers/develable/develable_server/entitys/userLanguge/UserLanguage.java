package devengers.develable.develable_server.entitys.userLanguge;

import devengers.develable.develable_server.entitys.userLanguge.enums.UserLanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private UserLanguageEnum userLanguageEnum;
}
