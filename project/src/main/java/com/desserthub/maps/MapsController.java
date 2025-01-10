package com.desserthub.maps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapsController {

    @GetMapping("/maps")
    public String getMethodName() {
        return "maps";
    }
    
}
