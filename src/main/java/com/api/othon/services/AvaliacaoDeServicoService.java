package com.api.othon.services;

import com.api.othon.model.AvaliacaoDeServico;
import com.api.othon.model.repository.AvaliacaoDeServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvaliacaoDeServicoService {

    @Autowired
    private AvaliacaoDeServicoRepository repository;

    public AvaliacaoDeServico save(AvaliacaoDeServico avaliacao) {
        return repository.save(avaliacao);
    }

    public Optional<AvaliacaoDeServico> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
