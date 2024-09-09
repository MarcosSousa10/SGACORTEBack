package com.api.othon.controller;

import com.api.othon.model.ContasAPagar;
import com.api.othon.services.ContasAPagarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contas-a-pagar")
public class ContasAPagarController {

    private final ContasAPagarService contasAPagarService;

    @Autowired
    public ContasAPagarController(ContasAPagarService contasAPagarService) {
        this.contasAPagarService = contasAPagarService;
    }

    @GetMapping
    public List<ContasAPagar> listarTodos() {
        return contasAPagarService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContasAPagar> buscarPorId(@PathVariable Long id) {
        Optional<ContasAPagar> conta = contasAPagarService.buscarPorId(id);
        return conta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ContasAPagar criar(@RequestBody ContasAPagar contasAPagar) {
        return contasAPagarService.salvar(contasAPagar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContasAPagar> atualizar(@PathVariable Long id, @RequestBody ContasAPagar contasAPagarAtualizado) {
        try {
            ContasAPagar atualizado = contasAPagarService.atualizar(id, contasAPagarAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        contasAPagarService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
