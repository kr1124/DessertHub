package com.desserthub.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MyController {
    // @GetMapping("/dessert") //@RequestParam 예제
    // public String searchDessert(@RequestParam String name, @RequestParam(required = false) String type) {
    //     return "Searching for dessert: " + name + " of type: " + type;
    // }

    // @PostMapping("/add") //post 예제
    // public String addDessert(@RequestBody Dessert dessert) {
    //     return "Added dessert: " + dessert.getName() + " with description: " + dessert.getDescription();
    // }

    // @PostMapping("/dessertImage") //파일 업로드 예제
    // public String uploadDessertImage(@RequestParam("file") MultipartFile file) {
    //     String filename = file.getOriginalFilename();
    //     // 파일 처리 로직 (저장 등)
    //     return "Uploaded file: " + filename;
    // }
}
