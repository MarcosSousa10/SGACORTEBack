package com.api.othon.controller;

import com.api.othon.model.Inventario;
import com.api.othon.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    @Autowired
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping
    public List<Inventario> listarTodos() {
        return inventarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarPorId(@PathVariable Long id) {
        Optional<Inventario> inventario = inventarioService.buscarPorId(id);
        return inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Inventario criar(@RequestBody Inventario inventario) {
        return inventarioService.salvar(inventario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> atualizar(@PathVariable Long id, @RequestBody Inventario inventarioAtualizado) {
        try {
            Inventario atualizado = inventarioService.atualizar(id, inventarioAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        inventarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
