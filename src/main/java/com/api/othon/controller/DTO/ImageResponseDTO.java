package com.api.othon.controller.dto;

public class ImageResponseDTO {
    private Long imageId;
    private String imageUrl;
    private String profissionalNome;

    // Construtor
    public ImageResponseDTO(Long imageId, String imageUrl, String profissionalNome) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.profissionalNome = profissionalNome;
    }

    // Getters e Setters
    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProfissionalNome() {
        return profissionalNome;
    }

    public void setProfissionalNome(String profissionalNome) {
        this.profissionalNome = profissionalNome;
    }
}
