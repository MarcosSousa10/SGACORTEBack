package com.api.othon.controller;
public class AlterarPrecoRequest {

    private Long usuarioId;
    private String novoPreco;

    // Getters e Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNovoPreco() {
        return novoPreco;
    }

    public void setNovoPreco(String novoPreco) {
        this.novoPreco = novoPreco;
    }
}
