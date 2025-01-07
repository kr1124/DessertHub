package com.desserthub.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TestController {
    
    @GetMapping("/test")
    public String getMethodName() {
        return "layout_test";
    }
    
    @GetMapping("/login")
    public String t_login() {
        return "member/login";
    }
    
}
