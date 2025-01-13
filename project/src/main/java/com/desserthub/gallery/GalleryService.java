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
}
