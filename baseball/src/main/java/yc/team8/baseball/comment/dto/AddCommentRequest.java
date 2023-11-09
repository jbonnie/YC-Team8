package yc.team8.baseball.comment.dto;

import lombok.Data;

@Data
public class AddCommentRequest {
    private String content;
    private Long postId;
}
