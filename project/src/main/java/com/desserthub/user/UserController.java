package com.desserthub.user;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desserthub.util.FileEncodingService;



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
            redirectAttributes.addFlashAttribute("message", "Login success");
            return "redirect:/home";
        } else {
            // 로그인 실패 시 다시 login.html로 이동
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    
    @PostMapping("/register")
    public String register_handler(@RequestParam("reg_name") String id, @RequestParam("reg_pass") String pass, @RequestParam("reg_email") String email, @RequestParam("reg_nickname") String nickname, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        UserService userService = new UserServiceImpl();
        UserDto userDto = new UserDto(id, pass, email, nickname);

        if(userService.register_check(userDto)) {
            session.setAttribute("username", id);
            redirectAttributes.addFlashAttribute("message", "Register success");
            return "redirect:/home";
        } else {
            // 회원가입 실패 시 다시 login.html로 이동
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    
    
    @GetMapping("/profile")
    public String request_get_user_profile() {
        //유저의 데이터를 DB에서 가져와 model, 혹은 session을 통해 뷰에 전달

        return "userProfile";
    }

    @GetMapping("/find")
    public String get_find() {
        return "find";
    }
    

    @PostMapping("/upload_pfp") //파일 업로드 예제
    public String uploadDessertImage(@RequestParam("file") MultipartFile file) {

        String filename = file.getOriginalFilename();
        FileEncodingService fileEncodingService = new FileEncodingService();

        try {
            // 파일을 Base64로 인코딩
            String base64Encoded = fileEncodingService.encodeFileToBase64(file);

            // Base64 인코딩된 문자열 반환
            return "Base64 encoded image: " + base64Encoded;

        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to process the file";
        }
        
        // return "Uploaded file: " + filename;
    }
}
