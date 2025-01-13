package com.desserthub.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {
    
    @GetMapping("/test")
    public String getMethodName() {
        return "test";
    }
    
    // @GetMapping("/login")
    // public String t_login() {
    //     return "member/login";
    // }
    
}
