package com.api.othon.controller;

import com.api.othon.model.ComunicacaoCliente;
import com.api.othon.model.ConfiguracaoDoSistema;
import com.api.othon.services.ConfiguracaoDoSistemaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/configuracoes")
public class ConfiguracaoDoSistemaController {

    @Autowired
    private ConfiguracaoDoSistemaService service;

    @PostMapping
    public ResponseEntity<ConfiguracaoDoSistema> create(@RequestBody ConfiguracaoDoSistema configuracao) {
        ConfiguracaoDoSistema savedConfig = service.save(configuracao);
        return ResponseEntity.ok(savedConfig);
    }
    @GetMapping
    public ResponseEntity<List<ConfiguracaoDoSistema>> testList() {
        List<ConfiguracaoDoSistema> configs = service.listarTodos();
        return ResponseEntity.ok(configs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ConfiguracaoDoSistema> getById(@PathVariable Long id) {
        Optional<ConfiguracaoDoSistema> configuracao = service.findById(id);
        return configuracao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/chave/{chave}")
    public ResponseEntity<ConfiguracaoDoSistema> getByChave(@PathVariable String chave) {
        ConfiguracaoDoSistema configuracao = service.findByChaveConfiguracao(chave);
        return ResponseEntity.ok(configuracao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfiguracaoDoSistema> update(@PathVariable Long id, @RequestBody ConfiguracaoDoSistema configuracao) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        configuracao.setId(id);
        ConfiguracaoDoSistema updatedConfig = service.save(configuracao);
        return ResponseEntity.ok(updatedConfig);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
