package com.desserthub.gallery;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class GalleryService {
    
    private final GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
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

    public void increaseLike(Long id) {
        Gallery gallery = galleryRepository.findById(id).orElseThrow(null);

        gallery.setGalleryLiked(gallery.getGalleryLiked() + 1);

        galleryRepository.save(gallery);
    }

    public void decreaseLike(Long id) {
        Gallery gallery = galleryRepository.findById(id).orElseThrow(null);

        if(gallery.getGalleryLiked() > 0) {
            gallery.setGalleryLiked(gallery.getGalleryLiked() - 1);
        }

        galleryRepository.save(gallery);
    }
}
