package com.desserthub.recommend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.desserthub.dessert.Dessert;
import com.desserthub.dessert.DessertService;

@Controller
@RequestMapping("/rec")
public class RecommendController {

    private final DessertService dessertservice;

    public RecommendController(DessertService dessertService){        
        this.dessertService = dessertService;
    }

    @GetMapping
    public String requestrec() {
        model.getAttribute("dessert", dessertService.getAllDesserts());
        return "dessert/list";
    }
    
    @GetMapping("/random")
    public String requestrecrnd() {
        model.getAttribute("dessert", dessertService.)
        return null;
    }
    
}
