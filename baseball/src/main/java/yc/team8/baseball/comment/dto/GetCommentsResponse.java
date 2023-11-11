package yc.team8.baseball.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import yc.team8.baseball.comment.Comment;
import yc.team8.baseball.user.domain.User;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class GetCommentsResponse {
    private Long id;
    private String nickname;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private boolean isWriter;

    public GetCommentsResponse(Comment comment, User user) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.isWriter = Objects.equals(comment.getUser().getId(), user.getId());
    }
}
