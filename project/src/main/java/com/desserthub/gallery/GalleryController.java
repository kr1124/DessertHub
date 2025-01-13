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

    @PostMapping("/upload")
    public String createGallery(@ModelAttribute Gallery gallery) {
        // gallery 객체는 폼 필드의 값으로 자동 바인딩됨 -> gallery 객체 자동 생성
        // 예시로, 이미지를 처리하는 로직을 추가할 수 있습니다
        
        // TODO 유저 아이디와 닉네임을 가져와 필드에 추가해야함.

        gallery.setG_liked(0);
        gallery.set_now();

        galleryService.createGallery(gallery);
        return "redirect:/gallery/main";
    }


    // POST 요청 처리: 폼 제출 후 데이터 처리
    // @PostMapping("/gallery")
    // public String submitGalleryForm(@ModelAttribute Gallery gallery, Model model) {
    //     // gallery 객체는 폼 필드의 값으로 자동 바인딩됨
    //     // 예시로, 이미지를 처리하는 로직을 추가할 수 있습니다
    //     String title = gallery.getG_title();
    //     String image = gallery.getG_img();
    //     String hashtags = gallery.getHashtags();

    //     // 여기서 이미지 데이터를 처리하거나, DB에 저장하거나 등의 로직을 추가할 수 있습니다.
        
    //     // 모델에 데이터를 추가하여 결과 페이지로 전달
    //     model.addAttribute("message", "업로드 성공!");
    //     model.addAttribute("gallery", gallery); // 폼에 입력된 데이터를 다시 모델에 담기

    //     return "galleryResult"; // galleryResult.html로 이동
    // }
    
    // 특정 이미지 조회 (GET 요청)
    @GetMapping("/gview/{id}")
    public String viewImage(@PathVariable Long id, Model model) {
        // id에 해당하는 Gallery 객체 찾기
        Gallery gallery = galleryService.getGallery(id).orElseThrow(() -> new RuntimeException("Gallery not found"));

        if (gallery != null) {
            // Gallery 객체를 모델에 추가
            model.addAttribute("gallery", gallery);
            return "gallery/galleryView"; // galleryView.html로 이동
        } else {
            model.addAttribute("message", "이미지를 찾을 수 없습니다.");
            return "error"; // error.html로 이동 (이미지가 없을 경우)
        }
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
