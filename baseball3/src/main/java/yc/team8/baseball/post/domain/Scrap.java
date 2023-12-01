package yc.team8.baseball.post.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Scrap implements Comparable<Scrap> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private String postType;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String postTitle;

    @CreatedDate
    private LocalDateTime createdAt;

    public Scrap(Long id, Long memberId, String teamName, String postType, Long postId, String postTitle) {
        this.id = id;
        this.memberId = memberId;
        this.teamName = teamName;
        this.postType = postType;
        this.postId = postId;
        this.postTitle = postTitle;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public int compareTo(Scrap scrap) {
        // 업데이트 최신순 정렬
        if(createdAt.compareTo(scrap.getCreatedAt()) > 0) {
            return -1;
        }
        else if(createdAt.compareTo(scrap.getCreatedAt()) < 0) {
            return 1;
        }
        else {      // 업데이트 시간이 동일할 경우
            if(id < scrap.getId()) {
                return 1;
            } else
                return -1;
        }
    }
}
