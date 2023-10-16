package yc.team8.baseball.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yc.team8.baseball.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
