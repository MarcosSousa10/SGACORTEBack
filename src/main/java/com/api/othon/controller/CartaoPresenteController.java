package com.api.othon.controller;

import com.api.othon.model.CartaoPresente;
import com.api.othon.services.CartaoPresenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartoes-presente")
public class CartaoPresenteController {

    private final CartaoPresenteService cartaoPresenteService;

    @Autowired
    public CartaoPresenteController(CartaoPresenteService cartaoPresenteService) {
        this.cartaoPresenteService = cartaoPresenteService;
    }

    @GetMapping
    public List<CartaoPresente> listarTodos() {
        return cartaoPresenteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoPresente> buscarPorId(@PathVariable Long id) {
        Optional<CartaoPresente> cartaoPresente = cartaoPresenteService.buscarPorId(id);
        return cartaoPresente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<CartaoPresente> buscarPorCodigo(@PathVariable String codigo) {
        CartaoPresente cartaoPresente = cartaoPresenteService.buscarPorCodigo(codigo);
        return cartaoPresente != null ? ResponseEntity.ok(cartaoPresente) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public CartaoPresente criar(@RequestBody CartaoPresente cartaoPresente) {
        return cartaoPresenteService.salvar(cartaoPresente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaoPresente> atualizar(@PathVariable Long id, @RequestBody CartaoPresente cartaoAtualizado) {
        try {
            CartaoPresente atualizado = cartaoPresenteService.atualizar(id, cartaoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cartaoPresenteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
