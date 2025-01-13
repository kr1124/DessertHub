package com.desserthub.dessert;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dessert")
public class DessertController {
    
    private final DessertService dessertService;

    public DessertController(DessertService dessertService) {
        this.dessertService = dessertService;
    }

    @GetMapping
    public String getAllDesserts(Model model) {
        model.addAttribute("dessert", dessertService.getAllDesserts());
        return "dessert/list";
    }

    @GetMapping("/{id}")
    public String getDessert(@PathVariable Long id, Model model) {
        model.addAttribute("dessert", dessertService.getDessert(id).orElseThrow(null));
        return "dessert/detail";
    }

}