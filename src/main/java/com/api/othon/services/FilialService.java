package com.api.othon.services;

import com.api.othon.model.Filial;
import com.api.othon.model.repository.FilialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilialService {

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
}
