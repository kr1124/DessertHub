package com.desserthub.recommend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.desserthub.dessert.DessertService;

@Controller
@RequestMapping("/rec")
public class RecommendController {

    private final DessertService dessertService;

    public RecommendController(DessertService dessertService){        
        this.dessertService = dessertService;
    }

    @GetMapping
    public String requestrec(Model model) {
        model.addAttribute("dessert", dessertService.getAllDesserts());
        return "dessert/list";
    }
    
    @GetMapping("/random")
    public String requestrecrnd(Model model) {
        model.addAttribute("dessert", dessertService);
        return null;
    }
    
}
