package com.desserthub.reply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // targetId로 Like 검색
    List<Reply> findByBoardId(Long boardId);

    List<Reply> findByUserId(Long userId);

}