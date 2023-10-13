package yc.team8.baseball.user.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDto {
    private Long id;
    private String userId;
    private String userPw;
    private String nickname;
    private String team;
}
