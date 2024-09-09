package com.api.othon.controller;

import com.api.othon.model.ComunicacaoCliente;
import com.api.othon.services.ComunicacaoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comunicacoes-clientes")
public class ComunicacaoClienteController {

    private final ComunicacaoClienteService comunicacaoClienteService;

    @Autowired
    public ComunicacaoClienteController(ComunicacaoClienteService comunicacaoClienteService) {
        this.comunicacaoClienteService = comunicacaoClienteService;
    }

    @GetMapping
    public List<ComunicacaoCliente> listarTodos() {
        return comunicacaoClienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunicacaoCliente> buscarPorId(@PathVariable Long id) {
        Optional<ComunicacaoCliente> comunicacaoCliente = comunicacaoClienteService.buscarPorId(id);
        return comunicacaoCliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ComunicacaoCliente criar(@RequestBody ComunicacaoCliente comunicacaoCliente) {
        return comunicacaoClienteService.salvar(comunicacaoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComunicacaoCliente> atualizar(@PathVariable Long id, @RequestBody ComunicacaoCliente comunicacaoClienteAtualizado) {
        try {
            ComunicacaoCliente atualizado = comunicacaoClienteService.atualizar(id, comunicacaoClienteAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        comunicacaoClienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
