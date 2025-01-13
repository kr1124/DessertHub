package com.desserthub.like;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//유저 프로필 화면 내에서 찜 목록만 보여줄 것이기 때문에, 즉 뷰는 이미 유저 컨트롤러 쪽에서 담당하므로 RestController 사용
@RestController
@RequestMapping("/like")
public class LikeController {
    
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public String getAllLikes(Model model) {
        model.addAttribute("like", likeService.getAllLikes());
        return "like/list";
    }

    @PostMapping
    public String createLike(@ModelAttribute Like like) {
        likeService.createLike(like);
        return "redirect:/like";
    }

    @GetMapping("/{id}")
    public String getLike(@PathVariable Long id, Model model) {
        model.addAttribute("like", likeService.getLike(id).orElseThrow(null));
        return "like/detail";
    }    

    @PostMapping("/{id}/delete")
    public String deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
        return "redirect:/like";
    }
}
