package com.api.othon.controller;

import com.api.othon.model.ProgramaFidelidade;
import com.api.othon.services.ProgramaFidelidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/programa-fidelidade")
public class ProgramaFidelidadeController {

    private final ProgramaFidelidadeService programaFidelidadeService;

    @Autowired
    public ProgramaFidelidadeController(ProgramaFidelidadeService programaFidelidadeService) {
        this.programaFidelidadeService = programaFidelidadeService;
    }

    @GetMapping
    public List<ProgramaFidelidade> listarTodos() {
        return programaFidelidadeService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaFidelidade> buscarPorId(@PathVariable Long id) {
        Optional<ProgramaFidelidade> programaFidelidade = programaFidelidadeService.buscarPorId(id);
        return programaFidelidade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProgramaFidelidade criar(@RequestBody ProgramaFidelidade programaFidelidade) {
        return programaFidelidadeService.salvar(programaFidelidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramaFidelidade> atualizar(@PathVariable Long id, @RequestBody ProgramaFidelidade programaAtualizado) {
        try {
            ProgramaFidelidade atualizado = programaFidelidadeService.atualizar(id, programaAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        programaFidelidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
