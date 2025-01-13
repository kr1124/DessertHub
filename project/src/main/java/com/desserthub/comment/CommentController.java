package com.desserthub.comment;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//내용만 rest api로 받아오므로 restController
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public String getAllComments(Model model) {
        model.addAttribute("comment", commentService.getAllComments());
        return "comment/list";
    }

    @GetMapping("/new")
    public String createCommentForm(Model model) {
        model.addAttribute("comment", new Comment());
        return "comment/new";
    }

    @PostMapping
    public String createComment(@ModelAttribute Comment comment) {
        commentService.createComment(comment);
        return "redirect:/comment";
    }

    @GetMapping("/{id}")
    public String getComment(@PathVariable Long id, Model model) {
        model.addAttribute("comment", commentService.getComment(id).orElseThrow(null));
        return "comment/detail";
    }

    @PostMapping("/{id}/delete")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "redirect:/comment";
    }

    
}
