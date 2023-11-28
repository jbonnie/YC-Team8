package yc.team8.baseball.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yc.team8.baseball.comment.domain.Comment;

import java.util.ArrayList;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글을 가져오기
    ArrayList<Comment> findAllByPostId(Long postId);
}
