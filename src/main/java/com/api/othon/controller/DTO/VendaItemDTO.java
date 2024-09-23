package com.api.othon.controller.DTO;

import lombok.Getter;
import lombok.Setter;


public class VendaItemDTO {
    private Long inventarioId; // Alterado de vendaItemId para inventarioId
    private Integer vendaItemQuantidade;

    public VendaItemDTO(Long inventarioId, Integer vendaItemQuantidade) {
        this.inventarioId = inventarioId;
        this.vendaItemQuantidade = vendaItemQuantidade;
    }

    // Getters e Setters
    public Long getInventarioId() {
        return inventarioId;
    }

    public void setInventarioId(Long inventarioId) {
        this.inventarioId = inventarioId;
    }

    public Integer getVendaItemQuantidade() {
        return vendaItemQuantidade;
    }

    public void setVendaItemQuantidade(Integer vendaItemQuantidade) {
        this.vendaItemQuantidade = vendaItemQuantidade;
    }
}
