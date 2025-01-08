package com.desserthub.board;

import java.util.List;

public interface BoardService {
    List<BoardDto> selectBoardList() throws Exception;
}
