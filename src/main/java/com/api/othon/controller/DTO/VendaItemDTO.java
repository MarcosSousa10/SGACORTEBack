package com.api.othon.controller.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaItemDTO {
    private Long vendaItemId;
    private Integer vendaItemQuantidade;

    public VendaItemDTO(Long vendaItemId, Integer vendaItemQuantidade) {
        this.vendaItemId = vendaItemId;
        this.vendaItemQuantidade = vendaItemQuantidade;
    }

    // Getters e Setters
}
