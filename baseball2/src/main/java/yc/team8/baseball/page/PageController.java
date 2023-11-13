package yc.team8.baseball.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yc.team8.baseball.post.service.PostService;

@Controller
public class PageController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    String home(Model model){
        // String teamName = userservice.getUserTeam(); // 유저의 선호 팀 정보 가져오기
        String teamName = "team1";
        model.addAttribute("teamName" , teamName);
        return "pages/home";
    }

    @GetMapping("/{teamName}")
    String otherTeam(@PathVariable String teamName, Model model){
        model.addAttribute("teamName" , teamName);
        return "pages/home";
    }
}
