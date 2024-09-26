package com.api.othon.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.othon.model.Image;
import com.api.othon.model.repository.ImageRepository;


@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image saveImage(byte[] imageData) {
        Image image = new Image();
        image.setData(imageData);
        return imageRepository.save(image);
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
    public void deleteImageById() {
        imageRepository.deleteAll();
    }
    
}