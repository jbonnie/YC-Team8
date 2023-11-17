package yc.team8.baseball.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yc.team8.baseball.member.domain.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String loginId;
    private String password;
    private String nickname;
    private String team;

    public Member toEntity() {
        return new Member(id, loginId, password, nickname, team);
    }
}
