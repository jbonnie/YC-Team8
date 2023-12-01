package yc.team8.baseball.scrap.domain;

import jakarta.persistence.*;
import lombok.*;
import yc.team8.baseball.member.domain.Member;
import yc.team8.baseball.post.domain.Post;

import static jakarta.persistence.FetchType.LAZY;

@Data
@Entity(name = "scrap")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@IdClass(ScrapId.class)
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
//    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private Member member;
//    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId")
    private Post post;
}
