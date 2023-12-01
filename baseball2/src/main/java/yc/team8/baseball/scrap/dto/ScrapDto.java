package yc.team8.baseball.scrap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import yc.team8.baseball.member.domain.Member;
import yc.team8.baseball.member.dto.MemberDto;
import yc.team8.baseball.post.domain.Post;
import yc.team8.baseball.post.dto.PostDto;
import yc.team8.baseball.scrap.domain.Scrap;

@Data
@NoArgsConstructor
public class ScrapDto {
    private Long id;
    private Long userId;
    private Long postId;

//    public ScrapDto(Long id, Long userId, Long postId) {
//        this.id = id;
//        this.userId = userId;
//        this.postId = postId;
//    }
    public Scrap toEntity(Member member, Post post) {
        Scrap scrap = new Scrap();
        scrap.setId(this.id);
        scrap.setMember(member);
        scrap.setPost(post);

        return scrap;
    }
}

//    public ScrapDto(Long userId, Long postId) {
//        this.userId = userId;
//        this.postId = postId;
//    }
