package com.api.othon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.api.othon.controller.dto.ImageResponseDTO;
import com.api.othon.model.Image;
import com.api.othon.services.ImageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            String imageUrl = "https://othondecarvalho.com.br:5555/images/" + image.getId();
            imageUrls.add(imageUrl);
        }

        return ResponseEntity.ok().body(imageUrls);
    }

    @GetMapping("/imagesOne")
    public ResponseEntity<List<ImageResponseDTO>> getImageUrlsOne() {
        List<Image> images = imageService.getAllImages();
        List<ImageResponseDTO> imageResponses = new ArrayList<>();

        for (Image image : images) {
            String profissionalNome = (image.getProfissional() != null) ? image.getProfissional().getNome() : "Profissional não associado";
            String imageUrl = "https://localhost:5555/images/" + image.getId();
            imageResponses.add(new ImageResponseDTO(image.getId(), imageUrl, profissionalNome));
        }

        return ResponseEntity.ok().body(imageResponses);
    }
    @GetMapping("/images/profissional/{profissionalId}")
    public ResponseEntity<byte[]> getImageByProfissionalId(@PathVariable Long profissionalId) {
        Image image = imageService.getImageByProfissionalId(profissionalId);
        
        if (image != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image.getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Image image = imageService.getImageById(id);
        if (image != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image.getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/images")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam("profissionalId") Long profissionalId) {
        try {
            byte[] imageData = file.getBytes();
            Image savedImage = imageService.saveImage(imageData, profissionalId);
            return ResponseEntity.ok().body("Image saved with ID: " + savedImage.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving image");
        }
    }

    @DeleteMapping("/images/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        try {
            imageService.deleteImageById(id);
            return ResponseEntity.ok().body("Image deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image.");
        }
    }
}
