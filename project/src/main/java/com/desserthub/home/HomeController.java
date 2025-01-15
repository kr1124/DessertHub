package com.desserthub.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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
    public String request_home(Model model) {
        String username = (String) model.getAttribute("username");
        if (username != null) {
            model.addAttribute("welcomeMessage", "Welcome, " + username + "!");
        }

        return "home";
    }

    @GetMapping("/remessage")
    public String redirect_handler() {
        return "remessage";
    }

    @GetMapping("/test-frag")
    public String test_frag() {
        return "test-fragments";
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

}
