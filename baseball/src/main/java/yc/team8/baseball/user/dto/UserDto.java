package yc.team8.baseball.user.dto;

import lombok.*;
import yc.team8.baseball.user.domain.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDto {
    private Long id;
    private String loginId;
    private String password;
    private String nickname;
    private String team;

    public User toEntity() {
        return new User(id, loginId, password, nickname, team);
    }
}
