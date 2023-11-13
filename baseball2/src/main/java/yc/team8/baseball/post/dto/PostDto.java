package yc.team8.baseball.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import yc.team8.baseball.post.domain.Post;

import java.util.Date;

@AllArgsConstructor
@Data
public class PostDto {

    private Long id;
    private int postType;   // 0: information, 1: question, 2: talk
    private String teamName;        // 응원하는 구단 이름
    private String title;
    private String contents;
    private long writerId;
    public Post toEntity() {
        return new Post(id, postType, teamName, title, contents, writerId);
    }
}
