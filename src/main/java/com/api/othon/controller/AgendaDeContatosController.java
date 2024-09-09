package com.api.othon.controller;

import com.api.othon.model.AgendaDeContatos;
import com.api.othon.services.AgendaDeContatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agendas-de-contatos")
public class AgendaDeContatosController {

    private final AgendaDeContatosService agendaDeContatosService;

    @Autowired
    public AgendaDeContatosController(AgendaDeContatosService agendaDeContatosService) {
        this.agendaDeContatosService = agendaDeContatosService;
    }

    @GetMapping
    public List<AgendaDeContatos> listarTodos() {
        return agendaDeContatosService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDeContatos> buscarPorId(@PathVariable Long id) {
        Optional<AgendaDeContatos> agendaDeContatos = agendaDeContatosService.buscarPorId(id);
        return agendaDeContatos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AgendaDeContatos criar(@RequestBody AgendaDeContatos agendaDeContatos) {
        return agendaDeContatosService.salvar(agendaDeContatos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaDeContatos> atualizar(@PathVariable Long id, @RequestBody AgendaDeContatos agendaDeContatosAtualizado) {
        try {
            AgendaDeContatos atualizado = agendaDeContatosService.atualizar(id, agendaDeContatosAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        agendaDeContatosService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
