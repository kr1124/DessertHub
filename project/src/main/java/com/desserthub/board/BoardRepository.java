package com.desserthub.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    
    List<Board> findByBoardContentContaining(String boardContent);

    List<Board> findByBoardTitleContaining(String boardTitle);

    List<Board> findByUserNnContaining(String userNn);

}
