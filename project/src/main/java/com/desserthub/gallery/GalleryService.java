package com.desserthub.gallery;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.desserthub.board.Board;
import com.desserthub.dlike.Dlike;
import com.desserthub.dlike.DlikeRepository;
import com.desserthub.reply.Reply;

@Service
public class GalleryService {
    
    private final GalleryRepository galleryRepository;
    private final DlikeRepository dlikeRepository;

    public GalleryService(GalleryRepository galleryRepository, DlikeRepository dlikeRepository) {
        this.galleryRepository = galleryRepository;
        this.dlikeRepository = dlikeRepository;
    }

    public List<Gallery> getAllGallerys() {
        return galleryRepository.findAll();
    }

    public Optional<Gallery> getGallery(Long id) {
        return galleryRepository.findById(id);
    }

    public Gallery createGallery(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    public void deleteGallery(Long id) {
        galleryRepository.deleteById(id);
    }

    // public void increaseLike(Long id) {
    //     Gallery gallery = galleryRepository.findById(id).orElseThrow(null);

    //     gallery.setGalleryLiked(gallery.getGalleryLiked() + 1);

    //     galleryRepository.save(gallery);
    // }

    // public void decreaseLike(Long id) {
    //     Gallery gallery = galleryRepository.findById(id).orElseThrow(null);

    //     if(gallery.getGalleryLiked() > 0) {
    //         gallery.setGalleryLiked(gallery.getGalleryLiked() - 1);
    //     }

    //     galleryRepository.save(gallery);
    // }
    
    public List<Gallery> getUserGallery(Long uid) {
        return galleryRepository.findByUserId(uid);
    }

    
    public void updateGalleryCount(Long gid) {
        List<Dlike> likeList = dlikeRepository.findByTargetIdAndTarget(gid, "gallery");

        Gallery gallery = galleryRepository.findById(gid).orElseThrow(null);
        
        gallery.setGalleryLiked(likeList.size());
        galleryRepository.save(gallery);
    }
}

