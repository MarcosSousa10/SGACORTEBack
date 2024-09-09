package com.api.othon.controller;

import com.api.othon.model.Transacao;
import com.api.othon.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public List<Transacao> listarTodas() {
        return transacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> buscarPorId(@PathVariable Long id) {
        Optional<Transacao> transacao = transacaoService.buscarPorId(id);
        return transacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transacao criar(@RequestBody Transacao transacao) {
        return transacaoService.salvar(transacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transacao> atualizar(@PathVariable Long id, @RequestBody Transacao transacaoAtualizada) {
        try {
            Transacao atualizado = transacaoService.atualizar(id, transacaoAtualizada);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        transacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
