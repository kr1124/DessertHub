package com.desserthub.board;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desserthub.user.User;
import com.desserthub.user.UserService;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;
    

    public BoardController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllBoards(Model model) {
        model.addAttribute("board", boardService.getAllBoards());
        return "board/list";
    }

    @GetMapping("/new")
    public String createBoardForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        if(session.getAttribute("userId") != null) {
            System.out.println("글 작성 페이지로 이동 1");
            model.addAttribute("board", new Board());
            System.out.println("글 작성 페이지로 이동 2");
            return "board/new";
        } else {
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
            redirectAttributes.addFlashAttribute("message", "글 작성은 로그인해야 할 수 있습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/login");
            return "redirect:/remessage";
        }
            
        // model.addAttribute("board", new Board());
        // return "board/new";
    }

    @PostMapping("/upload")
    public String createBoard(@ModelAttribute Board board, HttpSession session, RedirectAttributes redirectAttributes) {
        System.out.println("일단 업로드 실행은 됨");
        
        //UserService userService = new UserService(null);
        //System.out.println("일단 서비스까지도 됨");
        User user = userService.getUser((Long)session.getAttribute("userId")).orElseThrow(null);

        System.out.println("일단 유저 관련 클래스 로드도 됨");

        board.setUserId(user.getUserId());
        board.setUserNn(user.getUserNn());
        boardService.createBoard(board);

        System.out.println("일단 저장까지도 됨");

        redirectAttributes.addFlashAttribute("message", "등록되었습니다.");
        redirectAttributes.addFlashAttribute("target", "/board");
        return "redirect:/remessage";

        // return "redirect:/board";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        boardService.increaseView(id);//조회수 증가

        model.addAttribute("board", boardService.getBoard(id).orElseThrow(null));
        return "board/detail";
    }

    @GetMapping("/{id}/edit")
    public String editBoardForm(@PathVariable Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Board board = boardService.getBoard(id).orElseThrow(null);
        
        User user = userService.getUser((Long)session.getAttribute("userId")).orElseThrow(null);

        if(board.getUserId().equals(user.getUserId())) {
            model.addAttribute("board", boardService.getBoard(id).orElseThrow(null));
            return "board/edit";
        } else {
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
            redirectAttributes.addFlashAttribute("message", "작성자만 수정할 수 있습니다.");
            redirectAttributes.addFlashAttribute("target", "/board");
            return "redirect:/remessage";
        }

        
        // model.addAttribute("board", boardService.getBoard(id).orElseThrow(null));
        // return "board/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateBoard(@PathVariable Long id, HttpSession session, @ModelAttribute Board board, RedirectAttributes redirectAttributes) {

        // UserService userService = new UserService(null);
        // User user = userService.getUser((Long)session.getAttribute("userId")).orElseThrow(null);
        // board.setUserId(user.getUserId());
        // board.setUserNn(user.getUserNn());

        boardService.updateBoard(id, board);
        
        redirectAttributes.addFlashAttribute("message", "수정되었습니다.");
        redirectAttributes.addFlashAttribute("target", "/board");
        return "redirect:/remessage";
    }

    @PostMapping("/{id}/delete")
    public String deleteBoard(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boardService.deleteBoard(id);
        
        redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
        redirectAttributes.addFlashAttribute("target", "/board");
        return "redirect:/remessage";
        //return "redirect:/board";
    }

    
    @GetMapping("/{id}/like")
    public void likeBoard(@PathVariable Long id) {
        boardService.increaseLike(id);
    }
    
    @GetMapping("/{id}/unlike")
    public void unlikeBoard(@PathVariable Long id) {
        boardService.decreaseLike(id);
    }

}