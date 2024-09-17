package com.api.othon.controller;

import com.api.othon.model.Agendamento;
import com.api.othon.model.Vendas;
import com.api.othon.services.VendasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendas")
public class VendasController {

    private final VendasService vendasService;

    @Autowired
    public VendasController(VendasService vendasService) {
        this.vendasService = vendasService;
    }
    @GetMapping
    public List<Vendas> listarTodos() {
        return vendasService.listarTodos();
    }
    @PostMapping
    public ResponseEntity<Vendas> criarVenda(@RequestBody Vendas vendas) {
        Vendas novaVenda = vendasService.salvar(vendas);
        return ResponseEntity.ok(novaVenda);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendas> buscarVendaPorId(@PathVariable Long id) {
        Optional<Vendas> venda = vendasService.buscarPorId(id);
        return venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendas> atualizarVenda(@PathVariable Long id, @RequestBody Vendas vendasAtualizado) {
        try {
            Vendas vendaAtualizada = vendasService.atualizar(id, vendasAtualizado);
            return ResponseEntity.ok(vendaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        vendasService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
