package yc.team8.baseball.post.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Post implements Comparable<Post>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId", updatable = false)
    private Long id;

    @Column(name = "postType")
    private int postType;       // 0: information, 1: question, 2: talk

    @Column(name = "teamName", nullable = false)
    private String teamName;        // 응원하는 구단 이름

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "writerId")
    private long writerId;

    @Column(name = "writerNickname")
    private String writerNickname;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Post(Long id, int postType, String teamName, String title, String contents, long writerId, String writerNickname) {
        this.id = id;
        this.postType = postType;
        this.teamName = teamName;
        this.title = title;
        this.contents = contents;
        this.writerId = writerId;
        this.writerNickname = writerNickname;

    }

    @Override
    public int compareTo(Post post) {
        // 업데이트 최신순 정렬
        if(updatedAt.compareTo(post.getUpdatedAt()) > 0) {
            return -1;
        }
        else if(updatedAt.compareTo(post.getUpdatedAt()) < 0) {
            return 1;
        }
        else {      // 업데이트 시간이 동일할 경우
            if(id < post.getId()) {
                return 1;
            } else
                return -1;
        }
    }
}
