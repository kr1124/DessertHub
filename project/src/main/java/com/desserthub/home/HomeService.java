package com.desserthub.home;

import java.util.List;

import org.springframework.stereotype.Service;

import com.desserthub.board.Board;
import com.desserthub.board.BoardRepository;
import com.desserthub.gallery.GalleryRepository;
import com.desserthub.user.UserRepository;

@Service
public class HomeService {

    // private final BoardRepository boardRepository;
    // private final UserRepository userRepository;
    // private final GalleryRepository galleryRepository;

    // public HomeService(BoardRepository boardRepository, UserRepository userRepository, GalleryRepository galleryRepository) {
    //     this.boardRepository = boardRepository;
    //     this.userRepository = userRepository;
    //     this.galleryRepository = galleryRepository;
    // }


    // public void updateUserDB(Long uid) {
    //     List<Board> boardList = boardRepository.findByUserId(uid);
    //     User user = userRepository.findById(uid);

    //     for(Board board : boardList) {
    //         board.setUserNn(null);
    //     }
    // }
}
