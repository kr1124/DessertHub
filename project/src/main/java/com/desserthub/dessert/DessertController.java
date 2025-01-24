package com.desserthub.dessert;
import java.util.Random;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dessert")
public class DessertController {
    
    private final DessertService dessertService;

    public DessertController(DessertService dessertService) {
        this.dessertService = dessertService;
    }

    @GetMapping
    public String getAllDesserts(Model model) {
        // model.addAttribute("dessert", dessertService.getAllDesserts());
        return "game/game-index";
    }

    @GetMapping("/tournament")
    public String getTournamentPage(Model model) {
        // 디저트 목록을 가져오고 토너먼트 방식에 맞게 처리
        List<Dessert> desserts = dessertService.getAllDesserts();        
        model.addAttribute("dessert", desserts);        
        return "game/tournament"; 
    }

        // 성격 유형 테스트
    @GetMapping("/personality-test")
    public String getPersonalityTestPage(Model model) {
        
        List<Dessert> desserts = dessertService.getAllDesserts(); 
        model.addAttribute("desserts", desserts);
        
         return "game/personality-test";  // 성격 유형 테스트 페이지
    }

    @GetMapping("/personality-test/{id}")
    @ResponseBody
    public Dessert getPersonalityResult(@PathVariable int id, Model model) {
        // System.out.println("호출됨");
        List<Dessert> desserts = dessertService.getAllDesserts(); 
        Dessert dessert = desserts.get(id);
        
        return dessert;    
    }    

    @GetMapping("/api/desserts/all")
    public List<Dessert> getAllDesserts() {
        return dessertService.getAllDesserts(); // 디저트 정보를 데이터베이스에서 가져오는 서비스 메서드
    }
        
    @GetMapping("/random-draw")
    public String getRandomDessert(Model model) {
        List<Dessert> desserts = dessertService.getAllDesserts();
            Random random = new Random();
            int randomIndex = random.nextInt(desserts.size());
            Dessert randomDessert = desserts.get(randomIndex);            
            // 랜덤 디저트를 모델에 추가
            model.addAttribute("dessert", randomDessert);
        return "game/random-draw"; 
    }

    // @GetMapping("/sample")
    // public String sampleRandomDessert(Model model) {
    //     List<Dessert> desserts = dessertService.getAllDesserts();
    //         Random random = new Random();
    //         int randomIndex = random.nextInt(desserts.size());
    //         Dessert randomDessert = desserts.get(randomIndex);            
    //         // 랜덤 디저트를 모델에 추가
    //         model.addAttribute("dessert", randomDessert);
    //     return "game/sample"; 
    // }
}