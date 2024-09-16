package com.api.othon.controller;

import com.api.othon.model.Profissional;
import com.api.othon.services.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    @Autowired
    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    public List<Profissional> listarTodos() {
        return profissionalService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscarPorId(@PathVariable Long id) {
        Optional<Profissional> profissional = profissionalService.buscarPorId(id);
        return profissional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Profissional> buscarPorNome(@PathVariable String nome) {
        Optional<Profissional> profissional = profissionalService.buscarPorNome(nome);
        return profissional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public Profissional criar(@RequestBody Profissional profissional) {
        return profissionalService.salvar(profissional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> atualizar(@PathVariable Long id, @RequestBody Profissional profissionalAtualizado) {
        try {
            Profissional atualizado = profissionalService.atualizar(id, profissionalAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        profissionalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
