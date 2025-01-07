package com.desserthub.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.desserthub.home.service.HomeService;
import org.springframework.web.bind.annotation.RequestMethod;


// @RestController //메서드에서 반환하는 데이터가 자동으로 HTTP 응답 본문으로 변환됩니다.
// @RequestMapping("/home") ///home 경로로 시작하는 모든 요청을 처리하는 컨트롤러임을 정의합니다.
@Controller
public class HomeController {

    // private final HomeService homeService;

    // 생성자 주입
    // @Autowired
    // public HomeController(HomeService homeService) {
    //     this.homeService = homeService;
    // }

    // @RequestMapping(value = "/home", method=RequestMethod.GET)
    // public String home(HttpServletRequest request) {
    //     // 모델에 데이터 추가
    //     //model.addAttribute("message", "Welcome to Dessert Hub!");
    //     // home.html 템플릿을 렌더링
    //     return "content/home";  // "home.html" 파일을 반환
    // }

    @GetMapping("/home")
    // public String getMethodName(@RequestParam String param) {
    //     return "hom";
    // }
    public String getMethodName(HttpServletRequest request) {
        return "home";
    }
    

    // @GetMapping("/greet") // /home/greet URL로 GET 요청을 처리하는 메서드를 정의합니다.
    // public String greet() {
    //     return homeService.getGreeting(); // 서비스 계층의 메서드를 호출하여 비즈니스 로직을 수행하고, 결과를 반환합니다.
    // }

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
