package com.api.othon.services;

import com.api.othon.model.Filial;
import com.api.othon.model.ImageFiliais;
import com.api.othon.model.repository.FilialRepository;
import com.api.othon.model.repository.ImageFilialRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FilialService {
    @Autowired
    private ImageFilialRepository imageRepository;

    @Autowired
    private FilialRepository filialRepository;

    public List<Filial> getAllFiliais() {
        return filialRepository.findAll();
    }

    public Optional<Filial> getFilialById(Long id) {
        return filialRepository.findById(id);
    }

    public Filial saveFilial(Filial filial) {
        return filialRepository.save(filial);
    }

    public void deleteFilial(Long id) {
        filialRepository.deleteById(id);
    }
    @Transactional
    public Filial salvar(Filial filial, MultipartFile imagem) throws IOException {
        if (imagem == null || imagem.isEmpty()) {
            throw new IllegalArgumentException("A imagem não pode ser nula ou vazia");
        }

        ImageFiliais img = new ImageFiliais();
        img.setData(imagem.getBytes());  
        ImageFiliais imagemSalva = imageRepository.save(img); 

        filial.setImagem(imagemSalva);

        return filialRepository.save(filial);
    }
}
