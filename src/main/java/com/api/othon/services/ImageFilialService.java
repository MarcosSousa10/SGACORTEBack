package com.api.othon.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.othon.model.Filial;
import com.api.othon.model.ImageFiliais;
import com.api.othon.model.repository.FilialRepository;
import com.api.othon.model.repository.ImageFilialRepository;

import java.util.List;

@Service
public class ImageFilialService {
    private final ImageFilialRepository imageRepository;
    private final FilialRepository filialRepository; 

    @Autowired
    public ImageFilialService(ImageFilialRepository imageRepository, FilialRepository filialRepository) {
        this.imageRepository = imageRepository;
        this.filialRepository = filialRepository; 
    };
    public ImageFiliais salvar(ImageFiliais imagem) {
        return imageRepository.save(imagem);
    };
    public ImageFiliais saveImage(byte[] imageData, Long FilialId) {
        Filial Filial = filialRepository.findById(FilialId).orElseThrow(() -> 
            new RuntimeException("Profissional não encontrado com ID: " + FilialId));
        
        ImageFiliais image = new ImageFiliais();
        image.setData(imageData);
        image.setFilial(Filial); 
    
        ImageFiliais savedImage = imageRepository.save(image);
    
        Filial.setImagem(savedImage); 
        filialRepository.save(Filial); 
    
        return savedImage;
    };

    public ImageFiliais getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    };

    public List<ImageFiliais> getAllImages() {
        return imageRepository.findAll();
    };

    public void deleteImageById(Long id) {
        imageRepository.deleteById(id);
    };
};