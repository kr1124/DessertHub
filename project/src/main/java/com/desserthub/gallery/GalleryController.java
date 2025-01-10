package com.desserthub.gallery;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping
    public String getAllGallerys(Model model) {
        model.addAttribute("gallery", galleryService.getAllGallerys());
        return "gallery/main";
    }

    @GetMapping("/upload")
    public String createGalleryForm(Model model) {
        model.addAttribute("gallery", new Gallery());
        return "gallery/upload";
    }

    @PostMapping
    public String createGallery(@ModelAttribute Gallery gallery) {
        galleryService.createGallery(gallery);
        return "redirect:/gallery/main";
    }


    
    // 단일 갤러리 사진은 새 창이 아니므로 따로 설정해야 할것임.
    // @GetMapping("/{id}")
    // public String getGallery(@PathVariable String id, Model model) {
    //     model.addAttribute("board", galleryService.getGallery(id).orElseThrow(null));
    //     return "gallery/detail";
    // }

    @PostMapping("/{id}/delete")
    public String deleteGallery(@PathVariable Long id) {
        galleryService.deleteGallery(id);
        return "redirect:/gallery/main";
    }
}
