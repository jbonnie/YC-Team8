package yc.team8.baseball.page;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yc.team8.baseball.member.domain.Member;
import yc.team8.baseball.member.service.MemberService;
import yc.team8.baseball.post.service.PostService;

@Controller
public class PageController {

    @Autowired
    private PostService postService;
    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    String home(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        // 로그인 되어 있지 않을 경우 로그인 화면 띄우기
        if(session == null) {
            return "redirect:/login";
        }
        // 로그인 되어 있을 경우
        Long id = (Long)session.getAttribute("memberID");   // 세션에 보관된 유저의 id 가져오기
        Member member = memberService.getMemberWithId(id);
        // 만약 유저의 응원 구단이 없을 경우(none)
        if(member.getTeam().equals("none"))
            model.addAttribute("teamName", "LG");
        // 응원 구단이 있을 경우
        else
            model.addAttribute("teamName" , member.getTeam());
        return "pages/home";        // 홈 화면 띄우기
    }

    @GetMapping("/{teamName}")
    String otherTeam(@PathVariable String teamName, Model model){
        model.addAttribute("teamName" , teamName);
        return "pages/home";
    }
}
