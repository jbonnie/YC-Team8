package yc.team8.baseball.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yc.team8.baseball.member.domain.Member;
import yc.team8.baseball.member.dto.LoginDto;
import yc.team8.baseball.member.dto.MemberDto;
import yc.team8.baseball.member.service.MemberService;
import yc.team8.baseball.post.domain.Post;
import yc.team8.baseball.post.service.PostService;

import java.util.ArrayList;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private PostService postService;

    // 로그인 화면 띄우기
    @GetMapping("/login")
    public String showLogin() {
        return "member/login";
    }
    // 회원가입 화면 띄우기
    @GetMapping("/signup")
    public String showSignUp() {
        return "member/signUp";
    }
    // 로그인 시도 -> 검증
    @PostMapping("/login")
    public String login(LoginDto loginDto,
                        HttpServletRequest request,
                        RedirectAttributes rttr) {

        Member member = memberService.getMemberWithDto(loginDto);
        if(member == null) {        // 로그인 실패
            String failMessage = "Login failed. Please check your ID or password.";
            rttr.addFlashAttribute("msg", failMessage);
            return "redirect:/login";       // 로그인 화면으로 리다이렉트
        }
        // 로그인 성공 -> 세션 생성
        HttpSession session = request.getSession(true);
        // 세션에 회원 id (long) 보관
        session.setAttribute("memberID", member.getId());
        rttr.addFlashAttribute("msg", "Successful login.");
        return "redirect:/";        // 홈 화면으로 리다이렉트
    }
    // 회원가입 시도
    @PostMapping("/signup")
    public String signUp(MemberDto memberDto,
                         HttpServletRequest request,
                         RedirectAttributes rttr,
                         Model model) {

        // 중복 id 확인
        Member member = memberService.getMemberWithLoginId(memberDto.getLoginId());
        if(member != null) {        // 중복 ID 존재
            rttr.addFlashAttribute("msg", "Already existing ID. Please use another one.");
            return "redirect:/signup";
        }
        else {
            // 중복 닉네임 확인
            Member member2 = memberService.getMemberWithNickname(memberDto.getNickname());
            if(member2 != null) {       // 중복 닉네임 존재
                // 회원가입 창 다시 띄울 때 이미 작성한 ID와 password는 채워져 있게끔
                model.addAttribute("msg", "Already existing nickname. Please use another one.");
                model.addAttribute("member", memberDto);
                return "member/signUp";
            }
            else {      // 회원가입 성공
                // 멤버 저장
                Member new_member = memberService.createMember(memberDto);
                // 세션 생성
                HttpSession session = request.getSession(true);
                // 세션에 회원 id (long) 보관
                session.setAttribute("memberID", new_member.getId());
                rttr.addFlashAttribute("msg", "Welcome, " + memberDto.getNickname() + "!");
                return "redirect:/";        // 홈 화면으로 리다이렉트
            }
        }
    }
    // 마이페이지 띄우기
    @GetMapping("/mypage")
    public String myPage(HttpServletRequest request,
                         RedirectAttributes rttr,
                         Model model) {
        HttpSession session = request.getSession(false);
        // 현재 로그인 되어있지 않음
        if(session == null) {
            rttr.addFlashAttribute("msg", "Please log in first.");
            return "redirect:/login";        // 로그인 화면 반환
        }
        // 로그인 되어있음
        Long id = (Long)session.getAttribute("memberID");   // 세션에 보관된 유저의 id 가져오기
        Member member = memberService.getMemberWithId(id);
        model.addAttribute("member", member);
        // 유저가 작성한 게시물 목록 모델에 저장하기
        ArrayList<Post> postList = postService.getPostsByWriter(id);
        model.addAttribute("writePost", postList);
        // 유저가 스크랩한 게시물 목록 모델에 저장하기
        //
        //
        //
        return "member/myPage";
    }
    // 유저 정보 수정 화면 띄우기
    @GetMapping("/mypage/edit")
    public String editMember(HttpServletRequest request, Model model) {
        // 수정할 유저 정보 가져오기
        HttpSession session = request.getSession();
        Long id = (Long)session.getAttribute("memberID");
        Member member = memberService.getMemberWithId(id);
        // 모델에 수정할 유저 정보 저장하기
        model.addAttribute("member", member);
        return "member/editMember";
    }
    // 유저 정보 수정
    @PostMapping("/mypage/update")
    public String updateMember(MemberDto memberDto,
                               RedirectAttributes rttr,
                               HttpServletRequest request) {
        // 수정 전 원래 유저 객체 가져오기
        HttpSession session = request.getSession();
        Long id = (Long)session.getAttribute("memberID");
        Member original_member = memberService.getMemberWithId(id);
        // memberDto의 id에 원래 유저의 id 넣어주기
        memberDto.setId(original_member.getId());
        // 중복 id 확인
        Member member = memberService.getMemberWithLoginId(memberDto.getLoginId());
        if(member != null) {        // 중복 ID 존재
            if(member.getId().equals(original_member.getId())) {     // 유저가 자신의 아이디를 수정하지 않았을 경우
                // 중복 닉네임 확인
                Member member2 = memberService.getMemberWithNickname(memberDto.getNickname());
                if(member2 != null) {       // 중복 닉네임 존재
                    if(member2.getId().equals(original_member.getId())) {   // 유저가 자신의 닉네임도 수정하지 않았을 경우
                        // 유저 정보 업데이트 후 DB에 저장
                        memberService.updateMember(memberDto);
                        return "redirect:/mypage";      // 마이페이지로 리다이렉트
                    }
                    else {      // 다른 유저가 이미 사용 중인 닉네임
                        rttr.addFlashAttribute("msg", "Already existing nickname. Please use another one.");
                        return "redirect:/mypage/edit";
                    }
                }
                else {      // 수정 성공
                    // 유저 정보 업데이트 후 DB에 저장
                    memberService.updateMember(memberDto);
                    return "redirect:/mypage";      // 마이페이지로 리다이렉트
                }
            }
            else {      // 다른 유저가 이미 수정한 아이디를 사용
                rttr.addFlashAttribute("msg", "Already existing ID. Please use another one.");
                return "redirect:/mypage/edit";
            }
        }
        else {
            // 중복 닉네임 확인
            Member member2 = memberService.getMemberWithNickname(memberDto.getNickname());
            if(member2 != null) {       // 중복 닉네임 존재
                if(member2.getId().equals(original_member.getId())) {     // 유저가 자신의 닉네임은 수정하지 않았을 경우
                    // 유저 정보 업데이트 후 DB에 저장
                    memberService.updateMember(memberDto);
                    return "redirect:/mypage";      // 마이페이지로 리다이렉트
                }
                else {      // 다른 유저가 사용하는 닉네임일 경우
                    rttr.addFlashAttribute("msg", "Already existing nickname. Please use another one.");
                    return "redirect:/mypage/edit";
                }
            }
            else {      // 수정 성공
                // 유저 정보 업데이트 후 DB에 저장
                memberService.updateMember(memberDto);
                return "redirect:/mypage";      // 마이페이지로 리다이렉트
            }
        }
    }
    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, RedirectAttributes rttr) {
        HttpSession session = request.getSession();
        session.invalidate();
        rttr.addFlashAttribute("msg", "Successful logout");
        return "redirect:/login";       // 로그인 화면으로 리다이렉트
    }
    // 회원탈퇴
    @GetMapping("/withdrawal")
    public String withdraw(HttpServletRequest request,
                           RedirectAttributes rttr) {
        HttpSession session = request.getSession(false);
        if(session == null) {       // 현재 로그인 되어있지 않음
            rttr.addFlashAttribute("msg", "Please log in first.");
            return "redirect:/login";        // 로그인 화면으로 리다이렉트
        }
        Long id = (Long)session.getAttribute("memberID");   // 유저의 id 가져오기
        memberService.deleteMember(id);     // DB에서 유저 삭제
        session.invalidate();       // 세션 삭제
        rttr.addFlashAttribute("msg", "Successful withdrawal.");
        return "redirect:/login";       // 로그인 화면으로 리다이렉트
    }
}
