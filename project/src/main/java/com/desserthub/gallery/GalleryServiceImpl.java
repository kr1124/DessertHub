package com.desserthub.gallery;

import org.springframework.stereotype.Service;

@Service
public class GalleryServiceImpl implements GalleryService {
    @Override
    public GalleryDto get_gallery_data() {
        GalleryDAO galleryDAO = new GalleryDAO();
        GalleryDto gd = galleryDAO.get_gallery_data();
        // TODO Auto-generated method stub
        return gd;
    }
}
