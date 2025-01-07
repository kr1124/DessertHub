package com.desserthub.user.controller;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/user")
public class UserController {

    @GetMapping("/user") // /user URL로 GET 요청을 처리
    public String request_user() {
        return "user"; // resources/templates/userHome.html 템플릿 반환
    }

    @GetMapping("/profile")
    public String request_get_user_profile() {
        return "userProfile";
    }

    @PostMapping("/dessertImage") //파일 업로드 예제
    public String uploadDessertImage(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        // 파일 처리 로직 (저장 등)
        return "Uploaded file: " + filename;
    }
}
