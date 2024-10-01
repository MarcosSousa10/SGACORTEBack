package com.api.othon.controller.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicoDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
}
