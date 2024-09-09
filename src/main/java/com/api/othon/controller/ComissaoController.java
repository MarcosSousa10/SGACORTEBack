package com.api.othon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.othon.model.Comissao;
import com.api.othon.services.ComissaoService;

import java.util.List;

@RestController
@RequestMapping("/api/comissoes")
public class ComissaoController {

    @Autowired
    private ComissaoService comissaoService;

    // Listar todas as comissões
    @GetMapping
    public ResponseEntity<List<Comissao>> listarTodas() {
        return ResponseEntity.ok(comissaoService.listarTodas());
    }

    // Buscar uma comissão pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Comissao> buscarPorId(@PathVariable Long id) {
        return comissaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar uma nova comissão
    @PostMapping
    public ResponseEntity<Comissao> criar(@RequestBody Comissao comissao) {
        Comissao novaComissao = comissaoService.salvar(comissao);
        return ResponseEntity.ok(novaComissao);
    }

    // Atualizar uma comissão existente
    @PutMapping("/{id}")
    public ResponseEntity<Comissao> atualizar(@PathVariable Long id, @RequestBody Comissao comissaoAtualizada) {
        Comissao comissao = comissaoService.atualizar(id, comissaoAtualizada);
        return ResponseEntity.ok(comissao);
    }

    // Deletar uma comissão
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        comissaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
