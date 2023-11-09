package yc.team8.baseball.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yc.team8.baseball.comment.Comment;
import yc.team8.baseball.comment.dto.AddCommentRequest;
import yc.team8.baseball.comment.dto.GetCommentsResponse;
import yc.team8.baseball.comment.repository.CommentRepository;
import yc.team8.baseball.post.domain.Post;
import yc.team8.baseball.post.repository.PostRepository;
import yc.team8.baseball.user.domain.User;
import yc.team8.baseball.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addComment(AddCommentRequest req) {
        Post post = postRepository.findById(req.getPostId()).orElseThrow();
        User user = userRepository.findByEmail(SecurityUtil.getLoginEmail()).orElseThrow();

        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(req.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        commentRepository.save(comment);
    }

    public List<GetCommentsResponse> getAllComment(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        List<Comment> comments = commentRepository.findByPost(post);
        User user = userRepository.findByEmail(SecurityUtil.getLoginEmail()).orElseThrow();
        List<GetCommentsResponse> getCommentsResponseList = comments.stream()
                .map(x -> new GetCommentsResponse(x, user))
                .collect(Collectors.toList());

        return getCommentsResponseList;
    }

    public GetCommentsResponse getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        User user = userRepository.findByEmail(SecurityUtil.getLoginEmail()).orElseThrow();

        return new GetCommentsResponse(comment, user);
    }

    @Transactional
    public void EditComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        User user = userRepository.findByEmail(SecurityUtil.getLoginEmail()).orElseThrow();

        if (comment.getUser() == user) {
            comment.edit(content);
        }
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        User user = userRepository.findByEmail(SecurityUtil.getLoginEmail()).orElseThrow();
        if (comment.getUser() == user) {
            commentRepository.delete(comment);
        }
    }
}
