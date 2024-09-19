package com.api.othon.controller;

import com.api.othon.model.TransacaoInventario;
import com.api.othon.services.TransacaoInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacao-inventario")
public class TransacaoInventarioController {

    @Autowired
    private TransacaoInventarioService transacaoInventarioService;

    @GetMapping
    public List<TransacaoInventario> getAllTransacaoInventario() {
        return transacaoInventarioService.getAllTransacoesInventarios();
    }

    @PostMapping
    public TransacaoInventario createTransacaoInventario(@RequestBody TransacaoInventario transacaoInventario) {
        return transacaoInventarioService.saveTransacaoInventario(transacaoInventario);
    }

    @DeleteMapping("/{transacaoId}/{inventarioId}")
    public ResponseEntity<Void> deleteTransacaoInventario(@PathVariable Long transacaoId, @PathVariable Long inventarioId) {
        transacaoInventarioService.deleteTransacaoInventario(transacaoId, inventarioId);
        return ResponseEntity.noContent().build();
    }

    // Métodos adicionais conforme necessário
}
