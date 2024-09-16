package com.api.othon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<Comissao>> buscarPorProfissionalId(@PathVariable Long profissionalId) {
        List<Comissao> comissoes = comissaoService.buscarPorProfissionalId(profissionalId);
        if (comissoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comissoes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Comissao> buscarPorId(@PathVariable Long id) {
        return comissaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Comissao comissao) {
        try {
            System.out.println(comissao.toString());

            Comissao novaComissao = comissaoService.salvar(comissao);
            return ResponseEntity.ok("Comissão criada com sucesso");
        } catch (Exception e) {
            // Log do erro se necessário
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a comissão: " + e.getMessage());
        }
    }

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
