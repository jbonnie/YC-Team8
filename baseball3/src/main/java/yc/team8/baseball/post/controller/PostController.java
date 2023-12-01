package yc.team8.baseball.post.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yc.team8.baseball.comment.domain.Comment;
import yc.team8.baseball.comment.service.CommentService;
import yc.team8.baseball.member.domain.Member;
import yc.team8.baseball.member.service.MemberService;
import yc.team8.baseball.post.domain.Post;
import yc.team8.baseball.post.domain.Scrap;
import yc.team8.baseball.post.dto.PostDto;
import yc.team8.baseball.post.repository.ScrapRepository;
import yc.team8.baseball.post.service.PostService;

import java.util.ArrayList;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ScrapRepository scrapRepository;

    // 각 구단의 게시판 창 띄우기
    @GetMapping("/{teamName}/{postType}")
    public String showBoard(@PathVariable String teamName,
                            @PathVariable String postType,
                            HttpServletRequest request,
                            RedirectAttributes rttr,
                            Model model) {
        // 잡담 게시판으로 접근하려 할 경우 -> 현재 유저가 자격이 있는지 확인
        if(postType.equals("talk")) {
            HttpSession session = request.getSession(false);
            if(session == null)
                return "redirect:/login";
            Long id = (Long)session.getAttribute("memberID");
            Member member = memberService.getMemberWithId(id);
            if(!member.getTeam().equals(teamName)) {        // 자격이 없음
                String msg = "You're not a fan of " + teamName + "!";
                rttr.addFlashAttribute("msg", msg);
                return "redirect:/" + teamName;
            }
        }

        model.addAttribute("teamName", teamName);
        model.addAttribute("postType", postType);
        model.addAttribute("stringPostType", postType);

        // 해당 구단의 해당 게시판 글 목록 가져오기
        ArrayList<Post> postArr = postService.getPosts(teamName, postType);
        model.addAttribute("postList", postArr);        // 게시글 목록 모델에 저장
        return "pages/category";
    }
    // 각 구단의 게시판 글 하나 보여주기
    @GetMapping("/{teamName}/{postType}/{id}")
    public String showPost(@PathVariable String teamName,
                           @PathVariable String postType,
                           @PathVariable Long id,
                           HttpServletRequest request, Model model) {
        // 해당 구단의 게시글 하나 데이터 가져오기
        Post post = postService.getPost(id);
        // 모델에 게시판 글 엔티티, 구단 이름, 게시판 종류 저장
        model.addAttribute("post", post);
        model.addAttribute("teamName", teamName);
        model.addAttribute("stringPostType", postType);
        // 해당 게시글의 모든 댓글 가져와서 모델에 저장
        ArrayList<Comment> commentList = commentService.getComments(id);
        model.addAttribute("comments", commentList);
        // 현재 유저 모델에 저장
        HttpSession session = request.getSession();
        Long user_id = (Long)session.getAttribute("memberID");
        Member member = memberService.getMemberWithId(user_id);
        model.addAttribute("member", member);
        return "post/showPost";
    }
    // 게시판 글 등록할 수 있는 화면 띄우기
    @GetMapping("/{teamName}/{postType}/create")
    public String createPost(@PathVariable String teamName,
                             @PathVariable String postType,
                             HttpServletRequest request,
                             Model model) {
        model.addAttribute("teamName", teamName);
        model.addAttribute("postType", postType);
        model.addAttribute("intPostType", intPostType(postType));

        HttpSession session = request.getSession(false);
        Long id = (Long)session.getAttribute("memberID");
        Member member = memberService.getMemberWithId(id);

        model.addAttribute("memberId",member.getId().toString());
        return "post/createPost";
    }

    // 각 구단의 게시판 글 등록하기
    @PostMapping("/{teamName}/{postType}")
    public String savePost(@PathVariable String teamName,
                           @PathVariable String postType,
                           HttpServletRequest request,
                           PostDto postDto) {

        HttpSession session = request.getSession(false);
        Long member_id = (Long)session.getAttribute("memberID");// 세션 정보 가져오기
        String nickname = memberService.getMemberWithId(member_id).getNickname();

        postDto.setWriterNickname(nickname); //세션 아이디로 작성자의 닉네임을 조회하고 dto에 추가
        Post saved = postService.createPost(postDto);       // 게시글 db에 저장
        return "redirect:/" + teamName + "/" + postType + "/" + saved.getId();       // 등록한 게시글 화면으로 리다이렉트
    }


    // 게시판에서 수정할 게시글 화면 띄우기
    @GetMapping("/{teamName}/{postType}/{id}/edit")
    public String editPost(@PathVariable String teamName,
                           @PathVariable String postType,
                           @PathVariable Long id,
                           HttpServletRequest request,
                           RedirectAttributes rttr,
                           Model model) {

        // 해당 게시글의 작성자가 접근하는 건지 검사
        HttpSession session = request.getSession(false);
        if(session == null)
            return "redirect:/login";
        Long member_id = (Long)session.getAttribute("memberID");
        // 수정할 데이터 가져오기

        Post target = postService.getPost(id);

        if(target.getWriterId() != member_id) {     // 게시글 작성자가 아님
            rttr.addFlashAttribute("msg", "Only writer can revise it.");
            return "redirect:/" + teamName + "/" + postType + "/" + id;
        }
        // 모델에 수정할 게시글, 구단 이름, 게시판 종류 저장하기
        model.addAttribute("post", target);
        model.addAttribute("teamName", teamName);
        model.addAttribute("stringPostType", postType);
        return "post/editPost";
    }
    // 게시판에서 수정한 게시글 업데이트 후 다시 띄우기
    @PostMapping("/{teamName}/{postType}/{id}/update")
    public String updatePost(@PathVariable String teamName, @PathVariable String postType,
                             @PathVariable Long id, PostDto postDto) {
        Post saved = postService.updatePost(postDto);       // 게시물 업데이트 후 DB에 저장된 게시물 리턴
        return "redirect:/" + teamName + "/" + postType + "/" + saved.getId();       // 업데이트한 게시물 화면으로 리다이렉트
    }
    // 게시판에서 게시글 삭제
    @GetMapping("/{teamName}/{postType}/{id}/delete")
    public String deleteInfo(@PathVariable String teamName,
                             @PathVariable String postType,
                             @PathVariable Long id,
                             HttpServletRequest request,
                             RedirectAttributes rttr) {
        // 해당 게시글의 작성자가 접근하는 건지 검사
        HttpSession session = request.getSession(false);
        if(session == null)
            return "redirect:/login";
        Long member_id = (Long)session.getAttribute("memberID");
        // 삭제를 원하는 데이터 가져오기
        Post target = postService.getPost(id);

        if(target.getWriterId() != member_id) {     // 게시글 작성자가 아님
            rttr.addFlashAttribute("msg", "Only writer can delete it.");
            return "redirect:/" + teamName + "/" + postType + "/" + id;
        }

        // 게시글 작성자 확인 후 게시글 삭제
        postService.deletePost(id);
        // 스크랩 테이블에서도 삭제
        ArrayList<Scrap> scrapList = scrapRepository.findAllByPostId(id);
        for(int i = 0; i < scrapList.size(); i++) {
            Scrap scrap = scrapList.get(i);
            scrapRepository.delete(scrap);
        }
        rttr.addFlashAttribute("msg", "The deletion has been completed.");
        return "redirect:/" + teamName + "/" + postType;     // 해당 구단의 해당 게시판 목록으로 이동
    }

    // 게시글 스크랩하기
    @GetMapping("/{teamName}/{postType}/{id}/scrap")
    public String scrapPost(@PathVariable String teamName,
                            @PathVariable String postType,
                            @PathVariable Long id,
                            HttpServletRequest request,
                            RedirectAttributes rttr) {
        // 현재 유저 객체 가져오기
        HttpSession session = request.getSession(false);
        Long member_id = (Long)session.getAttribute("memberID");
        Member member = memberService.getMemberWithId(member_id);
        // 현재 게시물 객체 가져오기
        Post post = postService.getPost(id);
        // 이미 스크랩한 게시물인지 확인
        Scrap check = scrapRepository.findByMemberIdAndPostId(member_id, id).orElse(null);
        if(check != null) {     // 스크랩 삭제
            scrapRepository.delete(check);
            rttr.addFlashAttribute("msg", "Scrapping canceled.");
            return "redirect:/" + teamName + "/" + postType + "/" + id;
        }
        // 스크랩 객체 만들고 저장
        Scrap scrap = new Scrap(null, member_id, teamName, postType, id, post.getTitle());
        Scrap saved = scrapRepository.save(scrap);
        rttr.addFlashAttribute("msg", "Successful scrapping");
        return "redirect:/" + teamName + "/" + postType + "/" + id;
    }

    private int intPostType(String postType){
        int res = -1;
        switch(postType){
            case "information":
                res = 0;
                break;
            case "question":
                res = 1;
                break;
            case "talk":
                res = 2;
                break;
        }
        return res;
    } // posttype를 int로 넘기기 위한 도움 함수..
}
