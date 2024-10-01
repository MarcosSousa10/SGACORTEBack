package com.api.othon.controller;

import com.api.othon.controller.dto.AgendamentoDTO;
import com.api.othon.model.Agendamento;
import com.api.othon.model.Agendamento.Status;
import com.api.othon.services.AgendamentoService;

import org.hibernate.mapping.Map;
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
    @GetMapping("/BuscarPorNome")
    public ResponseEntity<List<Agendamento>> buscarPorNomeCliente(@RequestParam String nome) {
        List<Agendamento> agendamentos = agendamentoService.buscarPorNomeCliente(nome);
        if (agendamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(agendamentos);
    }
    @GetMapping
    public List<AgendamentoDTO> listarTodos() {
        return agendamentoService.listarTodos();
    }
    @GetMapping("/agendamentos/agendados")
    public List<Agendamento> listarAgendados() {
        return agendamentoService.listarAgendados();
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
    public ResponseEntity<Agendamento> atualizar(@PathVariable Long id,
            @RequestBody Agendamento agendamentoAtualizado) {
        try {
            Agendamento atualizado = agendamentoService.atualizar(id, agendamentoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    
    @PutMapping("/Atualizar/{id}")
    public ResponseEntity<Agendamento> atualizarStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO statusUpdateDTO) {
        try {
            Agendamento agendamentoExistente = agendamentoService.buscarPorIdStatus(id);
    
            if (agendamentoExistente == null) {
                return ResponseEntity.notFound().build();
            }
    
            if (statusUpdateDTO.getStatus() != null) {
                String statusStr = statusUpdateDTO.getStatus();
                // Usando Status diretamente, pois foi importado
                agendamentoExistente.setStatus(Status.valueOf(statusStr));
            }
    
            Agendamento atualizado = agendamentoService.atualizarStatus(agendamentoExistente);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        agendamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
