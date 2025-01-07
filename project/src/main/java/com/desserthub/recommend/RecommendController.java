package com.desserthub.recommend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/rec")
public class RecommendController {

    @GetMapping
    public String request_rec(@RequestParam String param) {
        return "recommendHome";
    }
    
    @GetMapping("/random")
    public String request_rec_random(@RequestParam String param) {
        return null;
    }
    
}
