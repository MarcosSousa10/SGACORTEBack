package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "vendas")
public class Vendas implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "inventario_id", nullable = true)
    private List<Inventario> inventario;

    @ElementCollection
    @Column(name = "quantidade", nullable = false)
    private List<Integer> quantidade;

    @Column(name = "data_venda", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVenda;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    private Filial filial;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pagamento", nullable = false)
    private MetodoPagamento metodoPagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @PrePersist
    protected void onCreate() {
        dataVenda = new Date();
    }

    public enum MetodoPagamento {
        DINHEIRO, CARTAO_CREDITO, CARTAO_DEBITO, PAGAMENTO_ONLINE
    }
}
