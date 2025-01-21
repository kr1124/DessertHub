package com.desserthub.board;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoard(Long id) {
        return boardRepository.findById(id);
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board updateBoard(Long id, Board boardDetails) {
        Board board = boardRepository.findById(id).orElseThrow(null);
        board.setBoardTitle(boardDetails.getBoardTitle());
        board.setBoardContent(boardDetails.getBoardContent());
        return boardRepository.save(board);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    public Board increaseView(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(null);

        board.setBoardView(board.getBoardView() + 1);
        return boardRepository.save(board);
    }

    public Board increaseLike(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(null);

        board.setBoardLiked(board.getBoardLiked() + 1);
        return boardRepository.save(board);
    }

    public Board decreaseLike(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(null);

        if(board.getBoardLiked() > 0) {
            board.setBoardLiked(board.getBoardLiked() - 1);
        }
        return boardRepository.save(board);
    }
}
