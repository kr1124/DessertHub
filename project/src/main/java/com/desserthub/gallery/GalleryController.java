package com.desserthub.gallery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GalleryController {

    @GetMapping("/gallery")
    public String get_gallery_data() {
        GalleryService galleryService = new GalleryServiceImpl();
        GalleryDto galleryDto = galleryService.get_gallery_data();

        return "gallery";
    }
}
