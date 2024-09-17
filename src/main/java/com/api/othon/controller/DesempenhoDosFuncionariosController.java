package com.api.othon.controller;

import com.api.othon.model.AvaliacaoDeServico;
import com.api.othon.model.DesempenhoDosFuncionarios;
import com.api.othon.services.DesempenhoDosFuncionariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/desempenho")
public class DesempenhoDosFuncionariosController {

    @Autowired
    private DesempenhoDosFuncionariosService service;

    @PostMapping
    public ResponseEntity<DesempenhoDosFuncionarios> create(@RequestBody DesempenhoDosFuncionarios desempenho) {
        DesempenhoDosFuncionarios savedDesempenho = service.save(desempenho);
        return ResponseEntity.ok(savedDesempenho);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesempenhoDosFuncionarios> getById(@PathVariable Long id) {
        Optional<DesempenhoDosFuncionarios> desempenho = service.findById(id);
        return desempenho.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public List<DesempenhoDosFuncionarios> listarTodos() {
        return service.listarTodos();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
