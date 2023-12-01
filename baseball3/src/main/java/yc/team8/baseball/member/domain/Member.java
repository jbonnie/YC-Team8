package yc.team8.baseball.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(length = 15, nullable = false)
    private String loginId;
    @Column(length = 20, nullable = false)
    private String password;
    @Column(length = 15, nullable = false)
    private String nickname;
    @Column(length = 15, nullable = false)
    private String team;
}
