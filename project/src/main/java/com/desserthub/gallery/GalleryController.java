package com.desserthub.gallery;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desserthub.dlike.DlikeService;
import com.desserthub.user.User;
import com.desserthub.user.UserService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

    private final GalleryService galleryService;
    private final UserService userService;
    private final DlikeService dlikeService;

    public GalleryController(GalleryService galleryService, UserService userService, DlikeService dlikeService) {
        this.galleryService = galleryService;
        this.userService = userService;
        this.dlikeService = dlikeService;
    }

    @GetMapping
    public String getAllGallerys(Model model, HttpSession session) {
        Long uid = (Long)session.getAttribute("userId");

        if(uid != null) {
            model.addAttribute("likeListGallery", dlikeService.getUserLikes(uid, "gallery"));
        }

        model.addAttribute("galleryList", galleryService.getAllGallerys());
        return "gallery/main";
    }

    @GetMapping("/upload")
    public String createGalleryForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if(session.getAttribute("userId") != null) {
            model.addAttribute("gallery", new Gallery());
            return "gallery/upload";
        } else {
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
            redirectAttributes.addFlashAttribute("message", "사진 업로드는 로그인해야 할 수 있습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/login");
            return "redirect:/remessage";
        }
    }

    @PostMapping("/upload")
    public String createGallery(@ModelAttribute Gallery gallery, HttpSession session, RedirectAttributes redirectAttributes) {
        Long uid = (Long)session.getAttribute("userId");

        User user = userService.getUser(uid).orElseThrow(null);
        
        gallery.setUserId(uid);
        gallery.setUserNn(user.getUserNn());

        gallery.setGalleryLiked(0);
        gallery.set_now();


        galleryService.createGallery(gallery);

        redirectAttributes.addFlashAttribute("message", "등록되었습니다.");
        redirectAttributes.addFlashAttribute("target", "/gallery");
        return "redirect:/remessage";
    }
    
    // 특정 이미지 조회 (GET 요청)
    @GetMapping("/gview/{id}")
    public String viewImage(@PathVariable Long id, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        // id에 해당하는 Gallery 객체 찾기
        Gallery gallery = galleryService.getGallery(id).orElseThrow(() -> new RuntimeException("Gallery not found"));

        if (gallery != null) {
            // Gallery 객체를 모델에 추가
            model.addAttribute("gallery", gallery);
            model.addAttribute("isLike", dlikeService.getLike(id, "gallery"));
            return "gallery/galleryView"; // galleryView.html로 이동
        } else {
            redirectAttributes.addFlashAttribute("message", "이미지를 찾을 수 없습니다..");
            redirectAttributes.addFlashAttribute("target", "/gallery");
            return "redirect:/remessage";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteGallery(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        galleryService.deleteGallery(id);
        
        redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
        redirectAttributes.addFlashAttribute("target", "/gallery");
        return "redirect:/remessage";
    }
    @PostMapping("/{id}/udelete")
    public String deleteGalleryInUserPage(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        galleryService.deleteGallery(id);
        
        redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
        redirectAttributes.addFlashAttribute("target", "/user/profile/manage-content");
        return "redirect:/remessage";
    }
}
