package com.api.othon.controller;

import com.api.othon.model.Filial;
import com.api.othon.services.FilialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filiais")
public class FilialController {

    @Autowired
    private FilialService filialService;

    @GetMapping
    public ResponseEntity<List<Filial>> getAllFiliais() {
        List<Filial> filiais = filialService.getAllFiliais();
        return ResponseEntity.ok(filiais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filial> getFilialById(@PathVariable Long id) {
        Optional<Filial> filial = filialService.getFilialById(id);
        return filial.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Filial> createFilial(@RequestBody Filial filial) {
        Filial createdFilial = filialService.saveFilial(filial);
        return ResponseEntity.ok(createdFilial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filial> updateFilial(@PathVariable Long id, @RequestBody Filial filial) {
        if (!filialService.getFilialById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        filial.setFilialId(id);
        Filial updatedFilial = filialService.saveFilial(filial);
        return ResponseEntity.ok(updatedFilial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilial(@PathVariable Long id) {
        if (!filialService.getFilialById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        filialService.deleteFilial(id);
        return ResponseEntity.noContent().build();
    }
}
