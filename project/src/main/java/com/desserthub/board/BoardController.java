package com.desserthub.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BoardController {
    
    @Autowired
    private BoardService boardService;

    @RequestMapping("/board")
    public ModelAndView request_board() throws Exception{
		ModelAndView mv = new ModelAndView("/apple/boardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list",list);
		
		return mv;
	}
}
