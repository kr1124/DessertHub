package com.desserthub.user;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 로그인, 회원가입 관련 컨트롤러
@Controller
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String request_login() {
        return "user/login";
    }

    @PostMapping("/login-check")
    public String login_handler(@ModelAttribute User login_user, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        if(userService.login_check(login_user)) {
            session.setAttribute("userCode", login_user.getUserCode());
            session.setAttribute("userId", login_user.getUserId());
            //아래는 session과는 다르게, 저장되지는 않는 일회성 세션임
            redirectAttributes.addFlashAttribute("message", "Login success");
            return "redirect:/home";
        } else {
            // 로그인 실패 시 다시 login.html로 이동
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @PostMapping("/register")
    public String register_handler(@ModelAttribute User reg_user, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        if(userService.register_check(reg_user)) {
            session.setAttribute("userCode", reg_user.getUserCode());
            session.setAttribute("userId", reg_user.getUserId());
            redirectAttributes.addFlashAttribute("message", "Register success");
            return "redirect:/home";
        } else { //해당 아이디 이미 존재
            // 회원가입 실패 시 다시 login.html로 이동
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    
    
    @GetMapping("/profile")
    public String request_get_user_profile(Model model, HttpSession session) {
        //유저의 데이터를 DB에서 가져와 model, 혹은 session을 통해 뷰에 전달
        User user = userService.getUser((Long)session.getAttribute("userCode")).orElseThrow(null);
        if(user == null) {
            // TODO
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
        }
        model.addAttribute("user", user);

        return "user/profile";
    }

    // 로그아웃 처리 및 세션 삭제
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 사용자 정보 삭제
        session.invalidate();  // 전체 세션을 무효화하여 로그아웃 처리
        return "redirect:/home";  // 홈 페이지로 리다이렉트
    }

    // 회원 탈퇴 처리 및 세션 삭제
    @GetMapping("/withdraw")
    public String withdraw(HttpSession session) {
        userService.deleteUser((Long)session.getAttribute("userCode"));
        session.invalidate();  // 전체 세션을 무효화하여 로그아웃 처리
        return "redirect:/home";  // 홈 페이지로 리다이렉트
    }

    @PostMapping("/upload-pfp") //파일 업로드 예제
    public String uploadDessertImage(String image64) {

        return "";
    }
}
