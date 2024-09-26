package com.api.othon.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.api.othon.model.Image;
import com.api.othon.services.ImageService;


    @RestController
    @CrossOrigin("*")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
@GetMapping("/images")
    public ResponseEntity<List<String>> getImageUrls() {
        List<Image> images = imageService.getAllImages();
        List<String> imageUrls = new ArrayList<>();

        for (Image image : images) {
            // Assuming you have a base URL where the images are served from
            String imageUrl = "https://othondecarvalho.com.br:5555/images/" + image.getId();
            imageUrls.add(imageUrl);
        }

        return ResponseEntity.ok().body(imageUrls);
    }
    
    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Image image = imageService.getImageById(id);
        byte[] imageData = image.getData();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) 
                .body(imageData);
    }
    @PostMapping("/images")
 public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){
         try {
            byte[] imageData = file.getBytes();
            Image savedImage = imageService.saveImage(imageData);
            return ResponseEntity.ok().body("Image saved with ID: " + savedImage.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving image");
        }
    }
     @DeleteMapping("/images")
    public ResponseEntity<String> deleteImage() {
        try {
            // Call the ImageService to delete the image by ID
            imageService.deleteImageById();
            return ResponseEntity.ok().body("Image deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image.");
        }
    }
}


