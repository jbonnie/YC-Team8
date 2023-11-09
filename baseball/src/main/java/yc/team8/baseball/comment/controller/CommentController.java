package yc.team8.baseball.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import yc.team8.baseball.comment.dto.AddCommentRequest;
import yc.team8.baseball.comment.dto.GetCommentsResponse;
import yc.team8.baseball.comment.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public String addComment(@RequestBody AddCommentRequest addCommentRequest) {
        commentService.addComment(addCommentRequest);
        return "created";
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetCommentsResponse> GetAllComment(@PathVariable Long postId) {
        return commentService.getAllComment(postId);
    }

    @GetMapping("/{postId}/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public GetCommentsResponse GetComment(@PathVariable Long postId, @PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    @PutMapping(value = "/{postId}/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public String EditComment(@PathVariable Long postId,
                              @PathVariable Long commentId,
                              @RequestBody String content) {
        commentService.EditComment(commentId, content);
        return "edit";
    }

    @DeleteMapping("/{postId}/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public String DeleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "delete";
    }
}
