package com.desserthub.dlike;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DlikeRepository extends JpaRepository<Dlike, Long> {
    
    // targetId로 Like 검색
    Dlike findByTargetIdAndTarget(Long targetId, String target);

    // userId와 targetId로 Like 검색
    Dlike findByUserIdAndTargetIdAndTarget(Long userId, Long targetId, String target);
}