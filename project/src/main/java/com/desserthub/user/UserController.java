package com.desserthub.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desserthub.board.BoardService;
import com.desserthub.dlike.DlikeService;
import com.desserthub.gallery.GalleryService;
import com.desserthub.reply.ReplyService;


// 로그인, 회원가입 관련 컨트롤러
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final DlikeService dlikeService;
    private final BoardService boardService;
    private final GalleryService galleryService;
    private final ReplyService replyService;

    public UserController(UserService userService, DlikeService dlikeService, BoardService boardService, GalleryService galleryService, ReplyService replyService) {
        this.userService = userService;
        this.dlikeService = dlikeService;
        this.boardService = boardService;
        this.galleryService = galleryService;
        this.replyService = replyService;
    }

    @GetMapping("/login")
    public String request_login(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @GetMapping("/find")
    public String request_find(Model model) {
        model.addAttribute("user", new User());
        return "user/find";
    }

    //로그인 폼 처리
    @PostMapping("/login-check")
    public String login_handler(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        if(userService.login_check(user)) {
            User tuser = userService.getUser(user.getUserId()).orElseThrow(null);
            session.setAttribute("userId", tuser.getId());
            //System.out.println("세션에 userId 저장됨: " + tuser.getId());  // 디버깅용 로그
            //아래는 session과는 다르게, 저장되지는 않는 일회성 메시지 전달이며 리다이렉트 시에도 유지됨
            redirectAttributes.addFlashAttribute("message", (String)tuser.getUserNn() + "님 로그인을 환영합니다.");
            //redirectAttributes.addFlashAttribute("message", "로그인 성공!");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            // 로그인 실패 시 다시 login.html로 이동
            redirectAttributes.addFlashAttribute("message", "아이디나 비밀번호가 맞지 않습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/login");
            return "redirect:/remessage";
        }
    }

    // 아이디 중복 확인
    @GetMapping("/check-username")
    @ResponseBody
    public Map<String, Object> checkUsername(@RequestParam String username) {
        Map<String, Object> response = new HashMap<>();
        
        // 아이디가 이미 존재하는지 확인
        boolean exists = userService.id_check(username);
        response.put("exists", exists);  // exists: true/false
        
        return response;  // JSON 형태로 반환
    }

    // 회원가입 폼 처리
    @PostMapping("/register")
    public String register_handler(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        if(userService.register_check(user)) {
            User tuser = userService.getUser(user.getUserId()).orElseThrow(null);
            session.setAttribute("userId", tuser.getId());
            redirectAttributes.addFlashAttribute("message", "회원가입되셨습니다.");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            // 회원가입 실패 시 다시 login.html로 이동
            redirectAttributes.addFlashAttribute("message", "회원가입 과정에서 에러가 발생했습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/login");
            return "redirect:/remessage";
        }
    }

    // 로그아웃 처리 및 세션 삭제
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        // 세션에서 사용자 정보 삭제
        session.invalidate();  // 전체 세션을 무효화하여 로그아웃 처리
        
        redirectAttributes.addFlashAttribute("message", "로그아웃 되었습니다.");
        redirectAttributes.addFlashAttribute("target", "/home");
        return "redirect:/remessage";
    }
    
    // id 찾기
    @PostMapping("/find-id")
    @ResponseBody
    public Map<String, Object> findId(@RequestParam String userNn, @RequestParam String userEm) {
        Map<String, Object> response = new HashMap<>();
        
        // 입력한 닉네임과 이메일로 사용자 조회
        User user = userService.find_id(userNn, userEm);
        
        if (user != null) {
            // 일치하는 사용자가 있으면 아이디 반환
            response.put("success", true);
            response.put("userId", user.getUserId());
        } else {
            // 일치하는 사용자가 없으면 실패 메시지 반환
            response.put("success", false);
        }

        return response;  // JSON 형태로 반환
    }

    //비밀번호 찾기
    @PostMapping("/find-pw")
    @ResponseBody
    public Map<String, Object> findPassword(@RequestParam String userId, @RequestParam String userEm) {
        Map<String, Object> response = new HashMap<>();
        
        // 아이디와 이메일로 사용자 조회
        User user = userService.find_pw(userId, userEm);
        
        if (user != null) {
            // 일치하는 사용자가 있으면 비밀번호 찾기 성공
            response.put("success", true);
            response.put("userPw", user.getUserPw());
        } else {
            // 일치하는 사용자가 없으면 실패 메시지 반환
            response.put("success", false);
        }

        return response;  // JSON 형태로 반환
    }
    

    @GetMapping("/profile")
    public String request_get_user_profile(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        //유저의 데이터를 DB에서 가져와 model을 통해 뷰에 전달
        User user = null;

        try {
            user = userService.getUser((Long)session.getAttribute("userId")).orElseThrow(null);
        } catch (Exception e) {
            
        }

        if(user == null) {
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
            redirectAttributes.addFlashAttribute("message", "잘못된 접근입니다.");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            model.addAttribute("user", user);
            return "user/profile";
        }
    }

    @GetMapping("/profile/edit-image")
    public String request_profile_image_edit(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        
        User user = userService.getUser((Long)session.getAttribute("userId")).orElseThrow(null);
        
        if(user == null) {
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
            redirectAttributes.addFlashAttribute("message", "잘못된 접근입니다.");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            model.addAttribute("user", user);
            return "user/profile/profile-edit-image";
        }
    }

    @GetMapping("/profile/edit")
    public String request_profile_edit(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.getUser((Long)session.getAttribute("userId")).orElseThrow(null);
        
        if(user == null) {
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
            redirectAttributes.addFlashAttribute("message", "잘못된 접근입니다.");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            model.addAttribute("user", user);
            return "user/profile/profile-edit";
        }

        
    }

    @GetMapping("/profile/favorites-list")
    public String request_fav(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Long uid = (Long)session.getAttribute("userId");

        if(uid == null) {
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
            redirectAttributes.addFlashAttribute("message", "잘못된 접근입니다.");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            model.addAttribute("likeListBoard", dlikeService.getUserLikes(uid, "board"));
            model.addAttribute("likeListGallery", dlikeService.getUserLikes(uid, "gallery"));
    
            return "user/profile/favorites-list";
        }
    }
    

    @GetMapping("/profile/manage-content")
    public String request_manage_content(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Long uid = (Long)session.getAttribute("userId");

        User user = userService.getUser(uid).orElseThrow(null);
        
        if(user == null) {
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
            redirectAttributes.addFlashAttribute("message", "잘못된 접근입니다.");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            model.addAttribute("boardList", boardService.getUserBoard(uid));
            model.addAttribute("galleryList", galleryService.getUserGallery(uid));
            model.addAttribute("replyList", replyService.getUserReply(uid));
            model.addAttribute("user", user);
            return "user/profile/manage-content";
        }
    }

    @GetMapping("/withdraw")
    public String request_withdraw(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.getUser((Long)session.getAttribute("userId")).orElseThrow(null);
        
        if(user == null) {
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
            redirectAttributes.addFlashAttribute("message", "잘못된 접근입니다.");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            model.addAttribute("user", user);
            return "user/profile/delete-account";
        }
    }
    

    @PostMapping("update")
    public String profile_update_handler(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        Long uid = (Long)session.getAttribute("userId");

        if(userService.updateUser(uid, user)) {
            redirectAttributes.addFlashAttribute("message", "수정되었습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/profile");
            return "redirect:/remessage";
        } else {
            redirectAttributes.addFlashAttribute("message", "오류가 발생하여 수정에 실패하였습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/profile");
            return "redirect:/remessage";
        }
    }

    @PostMapping("update/image")
    public String profile_image_update_handler(@ModelAttribute User user, RedirectAttributes redirectAttributes, HttpSession session) {

        if(userService.updateUserProfileImage((Long)session.getAttribute("userId"), user.getUserPi())) {
            redirectAttributes.addFlashAttribute("message", "수정되었습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/profile");
            return "redirect:/remessage";
        } else {
            redirectAttributes.addFlashAttribute("message", "오류가 발생하여 수정에 실패하였습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/profile");
            return "redirect:/remessage";
        }
    }
    

    // 회원 탈퇴 처리 및 세션 삭제
    @PostMapping("/delete")
    public String delet_user(@ModelAttribute User user, HttpSession session, RedirectAttributes redirectAttributes) {
        User tuser = userService.getUser(user.getUserId()).orElseThrow(null);

        if(tuser != null) {
            userService.deleteUser(user.getUserId(), user.getUserPw());
            session.invalidate();  // 전체 세션을 무효화하여 로그아웃 처리

            redirectAttributes.addFlashAttribute("message", "회원 탈퇴가 완료되었습니다. 이용해주셔서 감사합니다.");
            redirectAttributes.addFlashAttribute("target", "/home");
            return "redirect:/remessage";
        } else {
            redirectAttributes.addFlashAttribute("message", "아이디나 비밀번호가 일치하지 않습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/withdraw");
            return "redirect:/remessage";
        }
    }
}
