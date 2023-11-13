package yc.team8.baseball.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import yc.team8.baseball.post.domain.Post;
import yc.team8.baseball.post.dto.PostDto;
import yc.team8.baseball.post.service.PostService;

import java.util.ArrayList;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    // 각 구단의 게시판 창 띄우기
    @GetMapping("/{teamName}/{postType}")
    public String showBoard(@PathVariable String teamName, @PathVariable String postType, Model model) {
        model.addAttribute("teamName", teamName);
        model.addAttribute("postType", postType);
        // 해당 구단의 해당 게시판 글 목록 가져오기
        ArrayList<Post> infoArr = postService.getPosts(teamName, postType);
        model.addAttribute("postList", infoArr);        // 게시글 목록 모델에 저장
        return "pages/category";
    }
    // 각 구단의 게시판 글 하나 보여주기
    @GetMapping("/{teamName}/{postType}/{id}")
    public String showPost(@PathVariable String teamName, @PathVariable String postType,
                              @PathVariable Long id, Model model) {
        // 해당 구단의 게시글 하나 데이터 가져오기
        Post post = postService.getPost(id);
        // 모델에 게시판 글 엔티티, 구단 이름, 게시판 종류 저장
        model.addAttribute("post", post);
        model.addAttribute("teamName", teamName);
        model.addAttribute("postType", postType);
        return "post/showPost";
    }
    // 게시판 글 등록할 수 있는 화면 띄우기
    @GetMapping("/{teamName}/{postType}/create")
    public String createPost() {
        return "post/createPost";
    }
    // 각 구단의 게시판 글 등록하기
    @PostMapping("/{teamName}/{postType}")
    public String savePost(@PathVariable String teamName, @PathVariable String postType, PostDto postDto) {
        Post saved = postService.createPost(postDto);       // 게시글 db에 저장
        return "redirect:/" + teamName + "/" + postType + "/" + saved.getId();       // 등록한 게시글 화면으로 리다이렉트
    }
    // 게시판에서 수정할 게시글 화면 띄우기
    @GetMapping("/{teamName}/{postType}/{id}/edit")
    public String editPost(@PathVariable String teamName, @PathVariable String postType,
                           @PathVariable Long id, Model model) {
        // 수정할 데이터 가져오기
        Post target = postService.getPost(id);
        // 모델에 수정할 게시글, 구단 이름, 게시판 종류 저장하기
        model.addAttribute("post", target);
        model.addAttribute("teamName", teamName);
        model.addAttribute("postType", postType);
        return "post/editPost";
    }
    // 게시판에서 수정한 게시글 업데이트 후 다시 띄우기
    @PostMapping("/{teamName}/{postType}/{id}/update")
    public String updatePost(@PathVariable String teamName, @PathVariable String postType,
                             @PathVariable Long id, PostDto postDto) {
        // 해당 게시물을 수정할 자격이 있는지 확인 필요 (글 작성자 확인)
        //
        //
        Post saved = postService.updatePost(postDto);       // 게시물 업데이트 후 db에 저장된 게시물 리턴
        return "redirect:/" + teamName + "/" + postType + "/" + saved.getId();       // 업데이트한 게시물 화면으로 리다이렉트
    }
    // 게시판에서 게시글 삭제
    @GetMapping("/{teamName}/{postType}/{id}/delete")
    public String deleteInfo(@PathVariable String teamName, @PathVariable String postType,
                             @PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/" + teamName + "/" + postType;     // 해당 구단의 해당 게시판 목록으로 이동
    }
}
