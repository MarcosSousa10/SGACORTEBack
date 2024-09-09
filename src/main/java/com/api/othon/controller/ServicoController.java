package com.api.othon.controller;

import com.api.othon.model.Servico;
import com.api.othon.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public List<Servico> listarTodos() {
        return servicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        Optional<Servico> servico = servicoService.buscarPorId(id);
        return servico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Servico criar(@RequestBody Servico servico) {
        return servicoService.salvar(servico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody Servico servicoAtualizado) {
        try {
            Servico atualizado = servicoService.atualizar(id, servicoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
