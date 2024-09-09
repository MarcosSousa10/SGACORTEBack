package com.api.othon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.othon.services.LogAuditoriaService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ExemploController {

    private final LogAuditoriaService logAuditoriaService;

    @Autowired
    public ExemploController(LogAuditoriaService logAuditoriaService) {
        this.logAuditoriaService = logAuditoriaService;
    }

    @PostMapping("/alterar-preco")
    public String alterarPreco(@RequestParam Long usuarioId, @RequestParam String novoPreco, HttpServletRequest request) {
        // Lógica de alteração de preço...
        String acao = "Alteração de Preço";
        String detalhes = "Preço alterado para: " + novoPreco;
        String enderecoIp = request.getRemoteAddr(); // Captura o IP do usuário

        // Registra o log de auditoria
        logAuditoriaService.registrarLog(usuarioId, acao, detalhes, enderecoIp);

        return "Preço alterado com sucesso!";
    }
    @PostMapping("/alterar-precos")
public String alterarPreco(@RequestBody AlterarPrecoRequest request, HttpServletRequest httpRequest) {
    Long usuarioId = request.getUsuarioId();
    String novoPreco = request.getNovoPreco();
    String acao = "Alteração de Preço";
    String detalhes = "Preço alterado para: " + novoPreco;
    String enderecoIp = httpRequest.getRemoteAddr(); // Captura o IP do usuário

    // Registra o log de auditoria
    logAuditoriaService.registrarLog(usuarioId, acao, detalhes, enderecoIp);

    return "Preço alterado com sucesso!";
}
}
