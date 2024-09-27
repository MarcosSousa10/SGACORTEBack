package com.api.othon.controller.dto;

import com.api.othon.model.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfissionalDTO {
    private Long id;
    private String nome;
    private Image imagem;

    // Construtor que o Hibernate usará
    public ProfissionalDTO(Long id, String nome, Image imagem) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
    }
}
