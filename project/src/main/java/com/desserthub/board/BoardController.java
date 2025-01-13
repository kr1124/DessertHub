package com.desserthub.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public String getAllBoards(Model model) {
        model.addAttribute("board", boardService.getAllBoards());
        return "board/list";
    }

    @GetMapping("/new")
    public String createBoardForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/new";
    }

    @PostMapping
    public String createBoard(@ModelAttribute Board board) {
        boardService.createBoard(board);
        return "redirect:/board";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id).orElseThrow(null));
        return "board/detail";
    }

    @GetMapping("/{id}/edit")
    public String editBoardForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id).orElseThrow(null));
        return "board/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateBoard(@PathVariable Long id, @ModelAttribute Board board) {
        boardService.updateBoard(id, board);
        return "redirect:/board";
    }

    @PostMapping("/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/board";
    }
}