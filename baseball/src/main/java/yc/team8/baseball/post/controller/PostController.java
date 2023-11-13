package yc.team8.baseball.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yc.team8.baseball.post.domain.Post;
import yc.team8.baseball.post.dto.PostDto;
import yc.team8.baseball.post.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController

public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    private ResponseEntity<List<PostDto>> getAllPostList(){
        List<PostDto> list = postService.getAllPostList().stream().map(Post::toPostDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{boardId}")
    private ResponseEntity<List<PostDto>> getPostListByType(@PathVariable("boardId") int boardId){
        List<PostDto> list = postService.getPostListByType(boardId)
                .stream().map(Post::toPostDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{boardId}/{postId}")
    private ResponseEntity<PostDto> getPost(@PathVariable("postId") Long postId)
    {
        Optional<Post> post = postService.getPostById(postId);
        if (!post.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        PostDto postdto = post.get().toPostDto();

        return ResponseEntity.ok(postdto);
    }
}