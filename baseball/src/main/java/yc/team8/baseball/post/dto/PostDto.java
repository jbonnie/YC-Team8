package yc.team8.baseball.post.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;
import yc.team8.baseball.post.domain.Post;

@AllArgsConstructor
@ToString
public class PostDto {
    private Long id;
    private int postType;   // 0: information, 1: question, 2: talk
    private String title;
    private String contents;
    private long writerId;
    private boolean deletion;       // 게시글이 삭제되었을 경우 true, 삭제되지 않았을 경우 false

    public Post toEntity() {
        return new Post(id, postType, title, contents, writerId, deletion);
    }
}
