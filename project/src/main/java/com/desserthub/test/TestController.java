package com.desserthub.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {
    
    @GetMapping("/test")
    public String getMethodName() {
<<<<<<< HEAD
=======
        

>>>>>>> cb08cae4a3fffc35011a269917ed3ca4a4bbee88
        return "test";
    }
    
    // @GetMapping("/login")
    // public String t_login() {
    //     return "member/login";
    // }
    
}
