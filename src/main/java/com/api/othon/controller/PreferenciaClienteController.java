package com.api.othon.controller;

import com.api.othon.model.PreferenciaCliente;
import com.api.othon.services.PreferenciaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/preferencias")
public class PreferenciaClienteController {

    private final PreferenciaClienteService preferenciaClienteService;

    @Autowired
    public PreferenciaClienteController(PreferenciaClienteService preferenciaClienteService) {
        this.preferenciaClienteService = preferenciaClienteService;
    }

    @GetMapping
    public List<PreferenciaCliente> listarTodas() {
        return preferenciaClienteService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreferenciaCliente> buscarPorId(@PathVariable Long id) {
        Optional<PreferenciaCliente> preferencia = preferenciaClienteService.buscarPorId(id);
        return preferencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PreferenciaCliente criar(@RequestBody PreferenciaCliente preferenciaCliente) {
        return preferenciaClienteService.salvar(preferenciaCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreferenciaCliente> atualizar(@PathVariable Long id, @RequestBody PreferenciaCliente preferenciaAtualizada) {
        try {
            PreferenciaCliente atualizada = preferenciaClienteService.atualizar(id, preferenciaAtualizada);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        preferenciaClienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
