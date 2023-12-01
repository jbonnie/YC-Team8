package yc.team8.baseball.scrap.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import yc.team8.baseball.member.domain.Member;
import yc.team8.baseball.post.domain.Post;

public class ScrapId implements Serializable {
    private Long id;
    private Long member;
    private Long post;

    public ScrapId(){}
    public ScrapId(Long id, Long member, Long post){
        super();
        this.id = id;
        this.member = member;
        this.post = post;
    }
}
