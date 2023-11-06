package yc.team8.baseball.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yc.team8.baseball.user.Service.UserService;
import yc.team8.baseball.user.domain.User;
import yc.team8.baseball.user.dto.UserDto;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입 창 띄우기
    @GetMapping("/sign-up")
    public String newUserForm() {
        return "user/sign-up";
    }

    // 새로운 유저 회원가입 진행
    @PostMapping("/create")
    public String create(UserDto userDto, RedirectAttributes rttr, Model model) {
        // service에서 새로운 유저 엔티티 생성, db에 저장
        User created = userService.create(userDto);
        if(created != null) {
            // 홈 화면이 띄워질 때 남겨줄 메세지
            rttr.addFlashAttribute("msg", "Welcome!");
            // 홈 화면에 유저 id 넘겨주기
            model.addAttribute("id", created.getId());
            return "redirect:/page/home";      // 회원가입 성공시 홈 화면 띄우기
        }
        else {
            // 회원가입 화면이 다시 띄워질 때 남겨줄 메세지
            rttr.addFlashAttribute("msg", "Sign up failed. Please try again.");
            return "redirect:/user/sign-up";          // 회원가입 실패시 회원가입 창 다시 띄우기
        }
    }

    // 로그인 창 띄우기
    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    // 로그인 후 유저 정보 검사 -> 성공/실패에 따라 화면 출력
    @PostMapping("/check")
    public String loginResult(UserDto userDto, RedirectAttributes rttr, Model model) {
        User user = userService.checkUser(userDto);
        if(user != null) {      // 로그인 성공
            // 홈 화면이 띄워질 때 남겨줄 메세지
            rttr.addFlashAttribute("msg", "Welcome Back!");
            // 홈 화면에 유저 id 넘겨주기
            model.addAttribute("id", user.getId());
            return "redirect:/page/home";      // 홈 화면 띄우기
        }
        else {           // 로그인 실패
            // 다시 로그인 창을 띄울 때 남겨줄 메세지
            rttr.addFlashAttribute("msg", "Please check your ID or password.");
            return "redirect:/user/login";     // 로그인 화면 다시 띄우기
        }
    }

    // 마이페이지 - 사용자 정보 조회 (id 이용)
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        // id를 조회해 사용자 정보 가지오기
        User user = userService.getUser(id);
        // 모델에 데이터 등록하기
        model.addAttribute("user", user);
        // 뷰 페이지 반환하기
        return "user/myPage";
    }

    // 사용자 정보 수정 창 띄우기
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // id를 조회해 수정할 사용자 정보 가져오기
        User target = userService.getUser(id);
        // 모델에 데이터 등록하기
        model.addAttribute("user", target);
        // 뷰 페이지 반환하기
        return "user/edit";
    }

    // 수정한 사용자 정보 저장하기
    @PostMapping("/update")
    public String update(UserDto userDto) {
        // 수정한 데이터 DB에 저장하기
        User updated = userService.update(userDto);
        // 수정한 후의 마이페이지로 리다이렉트
        return "redirect:/user/" + updated.getId();
    }

    // 회원 탈퇴 - 사용자 삭제 (id 이용)
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        userService.delete(id);
        rttr.addFlashAttribute("msg", "Thanks to visit!");
        return "redirect:/user/login";     // 로그인 화면 다시 띄우기
    }
}
