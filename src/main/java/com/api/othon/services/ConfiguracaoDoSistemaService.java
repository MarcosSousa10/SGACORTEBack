package com.api.othon.services;

import com.api.othon.model.ConfiguracaoDoSistema;
import com.api.othon.model.repository.ConfiguracaoDoSistemaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfiguracaoDoSistemaService {

    @Autowired
    private ConfiguracaoDoSistemaRepository repository;

    public ConfiguracaoDoSistema save(ConfiguracaoDoSistema configuracao) {
        return repository.save(configuracao);
    }

    public Optional<ConfiguracaoDoSistema> findById(Long id) {
        return repository.findById(id);
    }

    public ConfiguracaoDoSistema findByChaveConfiguracao(String chaveConfiguracao) {
        return repository.findByChaveConfiguracao(chaveConfiguracao);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
