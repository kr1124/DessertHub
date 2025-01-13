package com.desserthub.like;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    public Optional<Like> getLike(Long id) {
        return likeRepository.findById(id);
    }

    public Like createLike(Like like) {
        return likeRepository.save(like);
    }

    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }

}
