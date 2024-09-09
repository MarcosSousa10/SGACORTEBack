package com.api.othon.controller;

import com.api.othon.model.PromocaoAtiva;
import com.api.othon.services.PromocaoAtivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/promocoes-ativas")
public class PromocaoAtivaController {

    private final PromocaoAtivaService promocaoAtivaService;

    @Autowired
    public PromocaoAtivaController(PromocaoAtivaService promocaoAtivaService) {
        this.promocaoAtivaService = promocaoAtivaService;
    }

    @GetMapping
    public List<PromocaoAtiva> listarTodos() {
        return promocaoAtivaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromocaoAtiva> buscarPorId(@PathVariable Long id) {
        Optional<PromocaoAtiva> promocaoAtiva = promocaoAtivaService.buscarPorId(id);
        return promocaoAtiva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/filial/{filialId}")
    public List<PromocaoAtiva> buscarPorFilial(@PathVariable Long filialId) {
        return promocaoAtivaService.buscarPorFilial(filialId);
    }

    @PostMapping
    public PromocaoAtiva criar(@RequestBody PromocaoAtiva promocaoAtiva) {
        return promocaoAtivaService.salvar(promocaoAtiva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromocaoAtiva> atualizar(@PathVariable Long id, @RequestBody PromocaoAtiva promocaoAtualizada) {
        try {
            PromocaoAtiva atualizada = promocaoAtivaService.atualizar(id, promocaoAtualizada);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        promocaoAtivaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
