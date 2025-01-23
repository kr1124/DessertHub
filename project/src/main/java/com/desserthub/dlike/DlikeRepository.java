package com.desserthub.dlike;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DlikeRepository extends JpaRepository<Dlike, Long> {
    
    // targetId로 Like 검색
    List<Dlike> findByTargetIdAndTarget(Long targetId, String target);

    // userId와 targetId로 Like 검색
    Dlike findByUserIdAndTargetIdAndTarget(Long userId, Long targetId, String target);

    // my page에서 씀
    List<Dlike> findByUserIdAndTarget(Long userId, String target);

    void deleteAllByUserId(Long userId);

}