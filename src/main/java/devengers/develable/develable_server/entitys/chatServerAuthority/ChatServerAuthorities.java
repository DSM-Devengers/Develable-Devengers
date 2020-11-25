package devengers.develable.develable_server.entitys.chatServerAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatServerAuthorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String serverId;

    private boolean canChatRoomCreate;

    private boolean canChatRoomUpdate;

    private boolean canServerDelete;

    private boolean canServerUpdate;
}
