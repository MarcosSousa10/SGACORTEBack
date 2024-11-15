package com.api.othon.controller;

import com.api.othon.controller.dto.AvaliacaoDTO;
import com.api.othon.model.AvaliacaoDeServico;
import com.api.othon.model.CampanhaDeMarketing;
import com.api.othon.services.AvaliacaoDeServicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoDeServicoController {

    @Autowired
    private AvaliacaoDeServicoService service;

    @PostMapping
    public ResponseEntity<AvaliacaoDeServico> create(@RequestBody AvaliacaoDeServico avaliacao) {
        AvaliacaoDeServico savedAvaliacao = service.save(avaliacao);
        return ResponseEntity.ok(savedAvaliacao);
    }
    @GetMapping("/testge")
    public List<AvaliacaoDeServico> listarTodosq() {
        return service.listarTodos1();
    }
    @GetMapping
    public List<AvaliacaoDTO> listarTodos() {
        return service.listarTodasAvaliacoes();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDeServico> getById(@PathVariable Long id) {
        Optional<AvaliacaoDeServico> avaliacao = service.findById(id);
        return avaliacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
