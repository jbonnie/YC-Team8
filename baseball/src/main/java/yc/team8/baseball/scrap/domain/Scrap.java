package yc.team8.baseball.scrap.domain;

import jakarta.persistence.*; //javax
import yc.team8.baseball.post.domain.Post;
import yc.team8.baseball.user.domain.User;

@Entity
@Table(name="scrap")
public class Scrap{

    @EmbeddedId
    private ScrapCompositeKey id; // composite pk

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name= "user_id")
    private User user;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name= "post_id")
    private Post post;
}
