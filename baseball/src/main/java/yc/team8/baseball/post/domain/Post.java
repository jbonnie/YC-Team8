package yc.team8.baseball.post.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yc.team8.baseball.post.dto.PostDto;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "postType")
    private int postType;       // 0: information, 1: question, 2: talk

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "contents")
    private String contents;

    @NonNull
    @Column(name = "writerId")
    private long writerId;

    @Column(name = "deletion", columnDefinition = "boolean default false")
    private boolean deletion;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Post(Long id, int postType, String title, String contents, long writerId, boolean deletion) {
        this.id = id;
        this.postType = postType;
        this.title = title;
        this.contents = contents;
        this.writerId = writerId;
        this.deletion = deletion;
    }

    public Post(int postType, String title, String contents, long writerId, boolean deletion) {
        this.postType = postType;
        this.title = title;
        this.contents = contents;
        this.writerId = writerId;
        this.deletion = deletion;
    }

    public PostDto toPostDto(){
        return new PostDto(id,postType,title,contents,writerId,deletion);
    }
}
