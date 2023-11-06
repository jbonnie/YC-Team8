package yc.team8.baseball.homepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    // information 게시판으로 가기
    @GetMapping("/informations")
    public String goInformation() {
        return "page/information";
    }

    // Question 게시판으로 가기
    @GetMapping("/questions")
    public String goQuestion() {
        return "page/question";
    }

    // talk 게시판으로 가기
    @GetMapping("/talks")
    public String goTalk() {
        // 현재 사용자의 응원 구단이 잡담방의 구단과 일치하는지 체크 구현 필요

        return "page/talk";
    }
}
