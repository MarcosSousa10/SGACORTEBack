package com.api.othon.model;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto implements Serializable {
  
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) 
   private Long codprod;
    private String descricao;
    private String unidade;
    private String produtopai;
    private Integer estoquecd;
    private Integer estoqueothon;
    private Integer estoquedispothon;
    private Integer qtachegar;
    private Integer giromes;
    private Integer qtvendida3meses;
   
    
  
}

