package yc.team8.baseball.comment.controller;

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
import yc.team8.baseball.comment.dto.CommentDto;
import yc.team8.baseball.comment.service.CommentService;
import yc.team8.baseball.member.domain.Member;
import yc.team8.baseball.member.service.MemberService;
import yc.team8.baseball.post.domain.Post;
import yc.team8.baseball.post.service.PostService;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PostService postService;

    // 댓글 등록
    @PostMapping("/{teamName}/{postType}/{id}/comment")
    public String createComment(CommentDto commentDto, @PathVariable String teamName,
                                @PathVariable String postType, @PathVariable Long id) {
        // 왜인지 모르겠으나, 넘어오는 comment dto에서 id 값이 null이 아닌 채로 넘어옴
        // 자꾸 id가 중복되어 기존의 댓글이 사라지는 현상 발생
        // 강제적으로 id null값으로 초기화해주기
        commentDto.setId(null);
        Comment saved = commentService.createComment(commentDto);       // 댓글을 새로 생성하여 DB에 저장
        return "redirect:/" + teamName + "/" + postType + "/" + id;     // 해당 게시글 화면으로 리다이렉트
    }

    // 댓글 수정 화면 띄우기
    @GetMapping("/{teamName}/{postType}/{post_id}/{comment_id}/edit")
    public String editComment(@PathVariable String teamName,
                              @PathVariable String postType,
                              @PathVariable Long post_id,
                              @PathVariable Long comment_id,
                              HttpServletRequest request,
                              RedirectAttributes rttr,
                              Model model) {
        // 현재 유저 객체 가져오기
        HttpSession session = request.getSession(false);
        if(session == null)
            return "redirect:/login";
        Long member_id = (Long)session.getAttribute("memberID");
        Member member = memberService.getMemberWithId(member_id);

        // 수정하고자 하는 타겟 댓글 가져오기
        Comment target = commentService.getComment(comment_id);

        // 현재 유저가 댓글 작성자가 아닐 경우
        if(!member.getNickname().equals(target.getNickname())) {
            rttr.addFlashAttribute("msg", "Only writer can revise it.");
            return "redirect:/" + teamName + "/" + postType + "/" + post_id;
        }

        // 작성자일 경우
        // 해당 구단의 게시글 하나 데이터 가져오기
        Post post = postService.getPost(post_id);
        // 모델에 게시판 글 엔티티, 구단 이름, 게시판 종류 저장
        model.addAttribute("post", post);
        model.addAttribute("teamName", teamName);
        model.addAttribute("stringPostType", postType);
        // 현재 유저 모델에 저장
        model.addAttribute("member", member);
        // 수정하고자 하는 댓글 모델에 저장
        model.addAttribute("comment", target);
        return "post/editComment";      // 수정 화면 띄우기
    }

    // 댓글 수정 후 해당 게시물 화면 다시 띄우기
    @PostMapping("/{teamName}/{postType}/{post_id}/{comment_id}/update")
    public String updateComment(@PathVariable String teamName,
                                @PathVariable String postType,
                                @PathVariable Long post_id,
                                @PathVariable Long comment_id,
                                CommentDto commentDto,
                                Model model) {

        Comment saved = commentService.updateComment(commentDto);     // 댓글 업데이트 후 DB에 저장된 댓글 리턴
        return "redirect:/" + teamName + "/" + postType + "/" + post_id;       // 업데이트한 게시물 화면으로 리다이렉트
    }

    // 댓글 삭제
    @GetMapping("/{teamName}/{postType}/{post_id}/{comment_id}/delete")
    public String deleteComment(@PathVariable String teamName,
                                @PathVariable String postType,
                                @PathVariable Long post_id,
                                @PathVariable Long comment_id,
                                HttpServletRequest request,
                                RedirectAttributes rttr) {
        // 지우고자 하는 타겟 댓글 가져오기
        Comment target = commentService.getComment(comment_id);

        // 현재 유저 객체 가져오기
        HttpSession session = request.getSession(false);
        if(session == null)
            return "redirect:/login";
        Long member_id = (Long)session.getAttribute("memberID");
        Member member = memberService.getMemberWithId(member_id);

        // 작성자가 아님
        if(!member.getNickname().equals(target.getNickname())) {
            rttr.addFlashAttribute("msg", "Only writer can delete it.");
            return "redirect:/" + teamName + "/" + postType + "/" + post_id;
        }
        // 만약 작성자 본인일 경우 -> 댓글 삭제
        else {
            commentService.deleteComment(comment_id);
            rttr.addFlashAttribute("msg", "The deletion has been completed.");
            return "redirect:/" + teamName + "/" + postType + "/" + post_id;     // 해당 게시글 화면으로 리다이렉트
        }
    }
}
