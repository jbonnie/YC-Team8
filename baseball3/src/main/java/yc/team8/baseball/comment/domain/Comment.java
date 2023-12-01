package yc.team8.baseball.comment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yc.team8.baseball.post.domain.Post;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Comment implements Comparable<Comment>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "postId", nullable = false)
    private Long postId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "contents", nullable = false)
    private String contents;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Comment(Long id, Long postId, String nickname, String contents) {
        this.id = id;
        this.postId = postId;
        this.nickname = nickname;
        this.contents = contents;
    }

    @Override
    public int compareTo(Comment comment) {
        // 업데이트 최신순 정렬
        if(updatedAt.compareTo(comment.getUpdatedAt()) > 0) {
            return -1;
        }
        else if(updatedAt.compareTo(comment.getUpdatedAt()) < 0) {
            return 1;
        }
        else {      // 업데이트 시간이 동일할 경우
            if(id < comment.getId()) {
                return 1;
            } else
                return -1;
        }
    }
}
