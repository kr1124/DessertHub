package com.desserthub.board;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desserthub.dlike.DlikeService;
import com.desserthub.reply.Reply;
import com.desserthub.reply.ReplyService;
import com.desserthub.user.User;
import com.desserthub.user.UserService;


@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;
    private final DlikeService dlikeService;
    private final ReplyService replyService;
    

    public BoardController(BoardService boardService, UserService userService, DlikeService dlikeService, ReplyService replyService) {
        this.boardService = boardService;
        this.userService = userService;
        this.dlikeService = dlikeService;
        this.replyService = replyService;
    }

    @GetMapping
    public String getAllBoards(Model model) {
        model.addAttribute("selected1", "all");
        model.addAttribute("selected2", "latest");
        model.addAttribute("board", boardService.getAllBoardsDesc());
        return "board/list";
    }

    @GetMapping("/order")
    public String getAllBoards(@RequestParam(name = "cate", defaultValue = "all") String category, @RequestParam(name = "ord", defaultValue = "latest") String order, Model model) {
        
        model.addAttribute("selected1", category);
        model.addAttribute("selected2", order);
        model.addAttribute("board", boardService.getBoards(category, order));

        return "board/list";
    }

    @GetMapping("/search")
    public String getAllBoardsBySearch(@RequestParam(name = "search", defaultValue = "title") String search, @RequestParam(name = "stext", defaultValue = "") String stext, Model model) {
        model.addAttribute("board", boardService.searchBoards(search, stext));
        return "board/list";
    }

    @GetMapping("/new")
    public String createBoardForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        if(session.getAttribute("userId") != null) {
            model.addAttribute("board", new Board());
            return "board/new";
        } else {
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
            redirectAttributes.addFlashAttribute("message", "글 작성은 로그인해야 할 수 있습니다.");
            redirectAttributes.addFlashAttribute("target", "/user/login");
            return "redirect:/remessage";
        }
    }

    @PostMapping("/upload")
    public String createBoard(@ModelAttribute Board board, HttpSession session, RedirectAttributes redirectAttributes) {

        User user = userService.getUser((Long)session.getAttribute("userId")).orElseThrow(null);

        board.setUserId(user.getUserId());
        board.setUserNn(user.getUserNn());
        boardService.createBoard(board);

        redirectAttributes.addFlashAttribute("message", "등록되었습니다.");
        redirectAttributes.addFlashAttribute("target", "/board");
        return "redirect:/remessage";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        boardService.increaseView(id);//조회수 증가

        model.addAttribute("board", boardService.getBoard(id).orElseThrow(null));
        model.addAttribute("isLike", dlikeService.getLike(id, "board"));
        model.addAttribute("replyList", replyService.getReplys(id));
        model.addAttribute("reply", new Reply());

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

    @PostMapping("/{id}/reply")
    public String addReply(@PathVariable Long id, @ModelAttribute Reply reply, HttpSession session, RedirectAttributes redirectAttributes) {

        if(session.getAttribute("userId") != null) {
            User user = userService.getUser((Long)session.getAttribute("userId")).orElseThrow(null);
    
            reply.setUserId(user.getId());
            reply.setUserNn(user.getUserNn());
            reply.setBoardId(id);
    
            replyService.createReply(reply);
            boardService.increaseComment(id);
            
            // redirectAttributes.addFlashAttribute("message", "작성되었습니다.");
            redirectAttributes.addFlashAttribute("target", "board/" + id);
            return "redirect:/remessage";
        } else {
            // 잘못된 접근이므로 경고와 함께 home으로 보내야함
            redirectAttributes.addFlashAttribute("message", "댓글 작성은 로그인해야 할 수 있습니다.");
            redirectAttributes.addFlashAttribute("target", "board/" + id);
            return "redirect:/remessage";
        }
    }

    @PostMapping("/{id}/dereply") //this id is reply's id
    public String deleteReply(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        System.out.println("일단 호출은 됨?");
        Reply reply = replyService.getReply(id).orElseThrow(null);
        Long boardId = reply.getBoardId();
        boardService.decreaseComment(boardId);

        replyService.deleteReply(id);
        
        // redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
        redirectAttributes.addFlashAttribute("target", "board/" + boardId);
        return "redirect:/remessage";
    }
    

}