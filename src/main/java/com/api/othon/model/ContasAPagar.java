package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "contas_a_pagar")
public class ContasAPagar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_fornecedor", nullable = false, length = 100)
    private String nomeFornecedor;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_vencimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    private Filial filial;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // Enum para status da conta
    public enum Status {
        PENDENTE, PAGO, ATRASADO
    }

    // Método para definir a data de criação automaticamente antes de persistir
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    // Método para definir a data de atualização automaticamente antes de atualizar
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
