package com.desserthub.dlike;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desserthub.board.BoardService;
import com.desserthub.gallery.Gallery;
import com.desserthub.gallery.GalleryService;
import com.desserthub.user.UserService;
import org.springframework.web.bind.annotation.RequestBody;



//유저 프로필 화면 내에서 찜 목록만 보여줄 것이기 때문에, 즉 뷰는 이미 유저 컨트롤러 쪽에서 담당하므로 RestController 사용
//하려 했으나 기존 방식이 익숙해서 컨트롤러로 변경
@Controller
@RequestMapping("/like")
public class DlikeController {
    
    private final DlikeService dlikeService;
    private final UserService userService;
    private final BoardService boardService;
    private final GalleryService galleryService;
    
    public DlikeController(DlikeService dlikeService, UserService userService, BoardService boardService, GalleryService galleryService) {
        this.dlikeService = dlikeService;
        this.userService = userService;
        this.boardService = boardService;
        this.galleryService = galleryService;        
    }

    @GetMapping
    public String getAllLikes(Model model) {
        model.addAttribute("like", dlikeService.getAllLikes());
        return "like/list";
    }

    @PostMapping
    public String createLike(@ModelAttribute Dlike dlike) {
        dlikeService.createLike(dlike);
        return "redirect:/like";
    }

    @GetMapping("/board/{id}/like")
    @ResponseBody
    public void likeBoard(@PathVariable Long id, HttpSession session) {
        
        Long userId = (Long)session.getAttribute("userId");

        if(userId != null) {
            if(dlikeService.getLike(userId, id, "board") == null) {
                Dlike dlike = new Dlike();
                dlike.setUserId(userId);
                dlike.setTarget("board");
                dlike.setTargetId(id);
                dlike.setTargetContent(boardService.getBoard(userId).orElseThrow(null).getBoardContent());
                
                boardService.increaseLike(id);

                dlikeService.createLike(dlike);
            }
        }  
    }
    @GetMapping("/board/{id}/unlike")
    @ResponseBody
    public void unlikeBoard(@PathVariable Long id, HttpSession session) {
        Dlike dlike = null;

        try {
            dlike = dlikeService.getLike((Long)session.getAttribute("userId"), id, "board");
        
            boardService.decreaseLike(id);

            dlikeService.deleteLike(dlike.getId());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @GetMapping("/gallery/{id}/like")
    @ResponseBody
    public void likeGallery(@PathVariable Long id, HttpSession session) {
        //System.out.println("좋아요 호출됨!!!!!!!!!");

        Long userId = (Long)session.getAttribute("userId");

        if(userId != null) {
            if(dlikeService.getLike(userId, id, "gallery") == null) {
                Dlike dlike = new Dlike();
                dlike.setUserId(userId);
                dlike.setTarget("gallery");
                dlike.setTargetId(id);
                dlike.setTargetContent(galleryService.getGallery(userId).orElseThrow(null).getGalleryImg());
                
                galleryService.increaseLike(id);

                dlikeService.createLike(dlike);
            }
        }   
    }

    @GetMapping("/gallery/{id}/unlike")
    @ResponseBody
    public void unlikeGallery(@PathVariable Long id, HttpSession session) {
        Dlike dlike = null;

        try {
            dlike = dlikeService.getLike((Long)session.getAttribute("userId"), id, "gallery");
        
            galleryService.decreaseLike(id);

            dlikeService.deleteLike(dlike.getId());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @GetMapping("/{id}")
    public String getLike(@PathVariable Long id, Model model) {
        model.addAttribute("like", dlikeService.getLike(id).orElseThrow(null));
        return "like/detail";
    }    

    @PostMapping("/user/")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @PostMapping("/{id}/delete")
    public void deleteLike(@PathVariable Long id) {
        dlikeService.deleteLike(id);
    }
}
