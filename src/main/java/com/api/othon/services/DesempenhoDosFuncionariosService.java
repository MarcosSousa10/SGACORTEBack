package com.api.othon.services;

import com.api.othon.model.AvaliacaoDeServico;
import com.api.othon.model.DesempenhoDosFuncionarios;
import com.api.othon.model.repository.DesempenhoDosFuncionariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesempenhoDosFuncionariosService {

    @Autowired
    private DesempenhoDosFuncionariosRepository repository;

    public DesempenhoDosFuncionarios save(DesempenhoDosFuncionarios desempenho) {
        return repository.save(desempenho);
    }
     public List<DesempenhoDosFuncionarios> listarTodos() {
        return repository.findAll();
    }

    public Optional<DesempenhoDosFuncionarios> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
