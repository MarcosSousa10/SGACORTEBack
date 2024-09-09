package com.api.othon.controller;

import com.api.othon.model.Agendamento;
import com.api.othon.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    public List<Agendamento> listarTodos() {
        return agendamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Long id) {
        Optional<Agendamento> agendamento = agendamentoService.buscarPorId(id);
        return agendamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agendamento criar(@RequestBody Agendamento agendamento) {
        return agendamentoService.salvar(agendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable Long id, @RequestBody Agendamento agendamentoAtualizado) {
        try {
            Agendamento atualizado = agendamentoService.atualizar(id, agendamentoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        agendamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
