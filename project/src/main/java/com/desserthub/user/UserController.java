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

    @GetMapping("/login")
    public String request_login() {
        return "login";
    }

    @PostMapping("/login-check")
    public String login_handler(@RequestParam("user_name") String id, @RequestParam("user_password") String pass, Model model,           RedirectAttributes redirectAttributes, HttpSession session) {

        UserService userService = new UserServiceImpl();

        if(userService.login_check(id, pass)) {
            session.setAttribute("username", id);

            //아래는 session과는 다르게, 저장되지는 않는 일회성 세션임
            redirectAttributes.addFlashAttribute("message", "Login successful!");
            return "redirect:/home";
        } else {
            // 로그인 실패 시 다시 login.html로 이동
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    
    
    @GetMapping("/profile")
    public String request_get_user_profile() {
        return "userProfile";
    }

    @GetMapping("/find")
    public String get_find() {
        return "find";
    }
    

    @PostMapping("/dessertImage") //파일 업로드 예제
    public String uploadDessertImage(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        // 파일 처리 로직 (저장 등)
        return "Uploaded file: " + filename;
    }
}
