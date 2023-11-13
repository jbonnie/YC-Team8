package yc.team8.baseball.post.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Post implements Comparable<Post>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "postType")
    private int postType;       // 0: information, 1: question, 2: talk

    @Column(name = "teamName")
    private String teamName;        // 응원하는 구단 이름

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "contents")
    private String contents;

    @NonNull
    @Column(name = "writerId")
    private long writerId;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Post(Long id, int postType, String teamName, String title, String contents, long writerId) {
        this.id = id;
        this.postType = postType;
        this.teamName = teamName;
        this.title = title;
        this.contents = contents;
        this.writerId = writerId;
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
