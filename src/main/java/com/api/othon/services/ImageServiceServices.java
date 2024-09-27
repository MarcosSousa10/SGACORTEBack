package com.api.othon.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.othon.model.imagemService;
import com.api.othon.model.Image;
import com.api.othon.model.Profissional; // Importando o Profissional
import com.api.othon.model.Servico;
import com.api.othon.model.repository.ImageServiceRepository;
import com.api.othon.model.repository.ServicoRepository;

import java.util.List;

@Service
public class ImageServiceServices {
    private final ImageServiceRepository imageRepository;
    private final ServicoRepository servicoRepository; // Adicionando repositório do Profissional

    @Autowired
    public ImageServiceServices(ImageServiceRepository imageRepository, ServicoRepository servicoRepository) {
        this.imageRepository = imageRepository;
        this.servicoRepository = servicoRepository; // Inicializando o repositório
    }
    public imagemService salvar(imagemService imagem) {
        return imageRepository.save(imagem);
    }
     public imagemService saveImage(byte[] imageData, Long profissionalId) {
        // Busca o profissional pelo ID
        Servico profissional = servicoRepository.findById(profissionalId).orElseThrow(() -> 
            new RuntimeException("Profissional não encontrado com ID: " + profissionalId));
        
        // Cria e salva a imagem
        imagemService image = new imagemService();
        image.setData(imageData);
        image.setServico(profissional); // Define o profissional na imagem
    
        // Salva a imagem
        imagemService savedImage = imageRepository.save(image);
    
        // Associar a imagem ao profissional
        profissional.setImagem(savedImage); // Certifique-se de que o método setImagem está correto
        servicoRepository.save(profissional); // Salva o profissional novamente
    
        return savedImage;
    }
    

    public List<imagemService> getAllImages() {
        return imageRepository.findAll();
    }

    public void deleteImageById(Long id) {
        imageRepository.deleteById(id); // Corrigido para deletar pela ID específica
    }
}
