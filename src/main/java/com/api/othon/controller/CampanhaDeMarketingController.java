package com.api.othon.controller;

import com.api.othon.model.CampanhaDeMarketing;
import com.api.othon.services.CampanhaDeMarketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/campanhas-de-marketing")
public class CampanhaDeMarketingController {

    private final CampanhaDeMarketingService campanhaDeMarketingService;

    @Autowired
    public CampanhaDeMarketingController(CampanhaDeMarketingService campanhaDeMarketingService) {
        this.campanhaDeMarketingService = campanhaDeMarketingService;
    }

    @GetMapping
    public List<CampanhaDeMarketing> listarTodos() {
        return campanhaDeMarketingService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaDeMarketing> buscarPorId(@PathVariable Long id) {
        Optional<CampanhaDeMarketing> campanhaDeMarketing = campanhaDeMarketingService.buscarPorId(id);
        return campanhaDeMarketing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CampanhaDeMarketing criar(@RequestBody CampanhaDeMarketing campanhaDeMarketing) {
        return campanhaDeMarketingService.salvar(campanhaDeMarketing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampanhaDeMarketing> atualizar(@PathVariable Long id, @RequestBody CampanhaDeMarketing campanhaDeMarketingAtualizada) {
        try {
            CampanhaDeMarketing atualizado = campanhaDeMarketingService.atualizar(id, campanhaDeMarketingAtualizada);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        campanhaDeMarketingService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
