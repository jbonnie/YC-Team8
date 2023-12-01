package yc.team8.baseball.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yc.team8.baseball.comment.domain.Comment;
import yc.team8.baseball.post.domain.Post;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private Long id;
    private Long postId;
    private String nickname;
    private String contents;

    public Comment toEntity() {
        return new Comment(id, postId, nickname, contents);
    }
}
