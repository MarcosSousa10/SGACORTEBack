package com.api.othon.controller;

import com.api.othon.model.Cliente;
import com.api.othon.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Endpoint para listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    // Endpoint para buscar um cliente pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para criar um novo cliente
    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.salvar(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    // Endpoint para atualizar um cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        Cliente cliente = clienteService.atualizar(id, clienteAtualizado);
        return ResponseEntity.ok(cliente);
    }

    // Endpoint para deletar um cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
