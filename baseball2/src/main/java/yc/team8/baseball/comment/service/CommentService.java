package yc.team8.baseball.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yc.team8.baseball.comment.domain.Comment;
import yc.team8.baseball.comment.dto.CommentDto;
import yc.team8.baseball.comment.repository.CommentRepository;
import yc.team8.baseball.post.domain.Post;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // 해당 id를 갖는 게시글의 모든 댓글 리스트 가져오기
    public ArrayList<Comment> getComments(Long id) {
        ArrayList<Comment> commentList = commentRepository.findAllByPostId(id);
        // ArrayList<Comment> 업데이트 시간 빠른 순으로 정렬
        Collections.sort(commentList);
        System.out.println("number of comments : " + commentList.size());
        return commentList;
    }

    // 해당 id를 가지는 댓글 가져오기
    public Comment getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        return comment;
    }

    // 해당 id를 가지는 댓글 삭제
    public void deleteComment(Long commentId) {
        Comment target = commentRepository.findById(commentId).orElse(null);
        if(target != null) {
            commentRepository.delete(target);
        }
    }

    // 새로운 댓글을 생성
    public Comment createComment(CommentDto commentDto) {
        Comment saved = commentRepository.save(commentDto.toEntity());
        return saved;
    }

    // 댓글 수정
    public Comment updateComment(CommentDto commentDto) {
        Comment target = commentRepository.findById(commentDto.getId()).orElse(null);
        if(target != null) {
            target.setContents(commentDto.getContents());
            commentRepository.save(target);
        }
        return target;
    }
}
