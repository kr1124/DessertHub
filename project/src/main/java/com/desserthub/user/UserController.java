package com.desserthub.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


// 로그인, 회원가입 관련 컨트롤러
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String request_login(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @GetMapping("/find")
    public String request_find(Model model) {
        model.addAttribute("user", new User());
        return "user/find";
    }

    @PostMapping("/login-check")
    public String login_handler(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        if(userService.login_check(user)) {
            User tuser = userService.getUser(user.getUserId()).orElseThrow(null);
            session.setAttribute("userId", tuser.getId());
            System.out.println("세션에 userId 저장됨: " + tuser.getId());  // 디버깅용 로그
            //아래는 session과는 다르게, 저장되지는 않는 일회성 메시지 전달이며 리다이렉트 시에도 유지됨
            redirectAttributes.addFlashAttribute("message", (String)tuser.getUserNn() + "님 로그인을 환영합니다.");
            //redirectAttributes.addFlashAttribute("message", "로그인 성공!");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            // 로그인 실패 시 다시 login.html로 이동
            redirectAttributes.addFlashAttribute("message", "아이디나 비밀번호가 맞지 않습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/login");
            return "redirect:/remessage";
        }
    }

    @PostMapping("/register")
    public String register_handler(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        if(userService.register_check(user)) {
            session.setAttribute("userId", user.getId());
            redirectAttributes.addFlashAttribute("message", "회원가입되셨습니다.");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            // 회원가입 실패 시 다시 login.html로 이동
            redirectAttributes.addFlashAttribute("message", "회원가입 과정에서 에러가 발생했습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/login");
            return "redirect:/remessage";
        }

        // session.setAttribute("userId", user.getId());
        // model.addAttribute("user", userService.createUser(user));
        // redirectAttributes.addFlashAttribute("message", "회원가입되셨습니다.");
        // redirectAttributes.addFlashAttribute("target", "/home");
        // return "redirect:/remessage";
    }
    
    @PostMapping("/find-id")
    public String find_id_handler(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        if(userService.login_check(user)) {
            User tuser = userService.getUser(user.getUserId()).orElseThrow(null);
            session.setAttribute("userId", tuser.getId());
            System.out.println("세션에 userId 저장됨: " + tuser.getId());  // 디버깅용 로그
            //아래는 session과는 다르게, 저장되지는 않는 일회성 메시지 전달이며 리다이렉트 시에도 유지됨
            redirectAttributes.addFlashAttribute("message", (String)tuser.getUserNn() + "님 로그인을 환영합니다.");
            //redirectAttributes.addFlashAttribute("message", "로그인 성공!");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            // 로그인 실패 시 다시 login.html로 이동
            redirectAttributes.addFlashAttribute("message", "아이디나 비밀번호가 맞지 않습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/login");
            return "redirect:/remessage";
        }
    }

    @PostMapping("/find-pw")
    public String find_pw_handler(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    

    @GetMapping("/profile")
    public String request_get_user_profile(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        //유저의 데이터를 DB에서 가져와 model을 통해 뷰에 전달
        User user = null;

        try {
            user = userService.getUser((Long)session.getAttribute("userId")).orElseThrow(null);
        } catch (Exception e) {
            // TODO: handle exception
        }

        if(user == null) {
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
            redirectAttributes.addFlashAttribute("message", "잘못된 접근입니다.");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            model.addAttribute("user", user);
            return "user/profile";
        }
    }

    // 로그아웃 처리 및 세션 삭제
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        // 세션에서 사용자 정보 삭제
        session.invalidate();  // 전체 세션을 무효화하여 로그아웃 처리
        
        redirectAttributes.addFlashAttribute("message", "로그아웃 되었습니다.");
        redirectAttributes.addFlashAttribute("target", "/home");
        return "redirect:/remessage";
    }

    // 회원 탈퇴 처리 및 세션 삭제
    @GetMapping("/withdraw")
    public String withdraw(HttpSession session, RedirectAttributes redirectAttributes) {
        userService.deleteUser((Long)session.getAttribute("userId"));
        session.invalidate();  // 전체 세션을 무효화하여 로그아웃 처리

        redirectAttributes.addFlashAttribute("message", "회원 탈퇴가 완료되었습니다. 이용해주셔서 감사합니다.");
        redirectAttributes.addFlashAttribute("target", "/home");
        return "redirect:/remessage";
    }

    @PostMapping("/upload-pfp") //파일 업로드 예제
    public String uploadDessertImage(String image64) {
        // TODO
        return "";
    }
}
