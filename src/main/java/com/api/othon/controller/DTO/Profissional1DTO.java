package com.api.othon.controller.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profissional1DTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String especialidade;
    private BigDecimal taxaComissao;

}

