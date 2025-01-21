package com.desserthub.like;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desserthub.user.User;
import com.desserthub.user.UserService;
import org.springframework.web.bind.annotation.RequestBody;



//유저 프로필 화면 내에서 찜 목록만 보여줄 것이기 때문에, 즉 뷰는 이미 유저 컨트롤러 쪽에서 담당하므로 RestController 사용
@RestController
@RequestMapping("/like")
public class LikeController {
    
    private final LikeService likeService;
    private final UserService userService;
    
    public LikeController(LikeService likeService, UserService userService) {
        this.likeService = likeService;
        this.userService = userService;
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

    @PostMapping("/board/{id}")
    public void likeBoard(@PathVariable Long id, HttpSession session) {
        Like like = new Like();
        like.setUserId((Long)session.getAttribute("userId"));
        like.setTarget("gallery");
        like.setTargetId(id);

        //좋아요 수 늘려야함.

        likeService.createLike(like);
    }

    @PostMapping("/gallery/{id}")
    public void likeGallery(@PathVariable Long id, HttpSession session) {
        Like like = new Like();
        like.setUserId((Long)session.getAttribute("userId"));
        like.setTarget("gallery");
        like.setTargetId(id);
        
        //좋아요 수 늘려야함.

        likeService.createLike(like);
    }

    @GetMapping("/{id}")
    public String getLike(@PathVariable Long id, Model model) {
        model.addAttribute("like", likeService.getLike(id).orElseThrow(null));
        return "like/detail";
    }    

    @PostMapping("/user/")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    

    // @PostMapping("/{id}/delete")
    // public String deleteLike(@PathVariable Long id) {
    //     likeService.deleteLike(id);
    //     return "redirect:/like";
    // }

    @PostMapping("/{id}/delete")
    public void deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
    }
}
