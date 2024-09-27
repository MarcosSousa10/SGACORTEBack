package com.api.othon.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.othon.model.Image;
import com.api.othon.model.Profissional; // Importando o Profissional
import com.api.othon.model.repository.ImageRepository;
import com.api.othon.model.repository.ProfissionalRepository; // Adicione o repositório do Profissional

import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProfissionalRepository profissionalRepository; // Adicionando repositório do Profissional

    @Autowired
    public ImageService(ImageRepository imageRepository, ProfissionalRepository profissionalRepository) {
        this.imageRepository = imageRepository;
        this.profissionalRepository = profissionalRepository; // Inicializando o repositório
    }
    public Image salvar(Image imagem) {
        return imageRepository.save(imagem);
    }
    public Image saveImage(byte[] imageData, Long profissionalId) {
        // Busca o profissional pelo ID
        Profissional profissional = profissionalRepository.findById(profissionalId).orElseThrow(() -> 
            new RuntimeException("Profissional não encontrado com ID: " + profissionalId));
        
        // Cria e salva a imagem
        Image image = new Image();
        image.setData(imageData);
        image.setProfissional(profissional); // Define o profissional na imagem
    
        // Salva a imagem
        Image savedImage = imageRepository.save(image);
    
        // Associar a imagem ao profissional
        profissional.setImagem(savedImage); // Certifique-se de que o método setImagem está correto
        profissionalRepository.save(profissional); // Salva o profissional novamente
    
        return savedImage;
    }
    
    public Image getImageByProfissionalId(Long profissionalId) {
        return imageRepository.findByProfissionalId(profissionalId); // Método para buscar a imagem pelo ID do profissional
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void deleteImageById(Long id) {
        imageRepository.deleteById(id); // Corrigido para deletar pela ID específica
    }
}
