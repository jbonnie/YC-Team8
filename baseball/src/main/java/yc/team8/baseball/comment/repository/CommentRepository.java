package yc.team8.baseball.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yc.team8.baseball.comment.Comment;
import yc.team8.baseball.post.domain.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}
