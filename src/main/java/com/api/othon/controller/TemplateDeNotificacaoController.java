package com.api.othon.controller;

import com.api.othon.model.TemplateDeNotificacao;
import com.api.othon.services.TemplateDeNotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/templates-de-notificacoes")
public class TemplateDeNotificacaoController {

    private final TemplateDeNotificacaoService templateDeNotificacaoService;

    @Autowired
    public TemplateDeNotificacaoController(TemplateDeNotificacaoService templateDeNotificacaoService) {
        this.templateDeNotificacaoService = templateDeNotificacaoService;
    }

    @GetMapping
    public List<TemplateDeNotificacao> listarTodos() {
        return templateDeNotificacaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateDeNotificacao> buscarPorId(@PathVariable Long id) {
        Optional<TemplateDeNotificacao> templateDeNotificacao = templateDeNotificacaoService.buscarPorId(id);
        return templateDeNotificacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TemplateDeNotificacao criar(@RequestBody TemplateDeNotificacao templateDeNotificacao) {
        return templateDeNotificacaoService.salvar(templateDeNotificacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemplateDeNotificacao> atualizar(@PathVariable Long id, @RequestBody TemplateDeNotificacao templateAtualizado) {
        try {
            TemplateDeNotificacao atualizado = templateDeNotificacaoService.atualizar(id, templateAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        templateDeNotificacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
