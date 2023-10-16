package yc.team8.baseball.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yc.team8.baseball.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
