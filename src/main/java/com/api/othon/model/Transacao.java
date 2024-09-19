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
@Table(name = "transacoes")
public class Transacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agendamento_id", nullable = true)
    private Agendamento agendamento;

    @ManyToOne
    @JoinColumn(name = "inventario_id", nullable = true)
    private Inventario inventario;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pagamento", nullable = false)
    private MetodoPagamento metodoPagamento;

    @Column(name = "valor_pago", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorPago;

    @Column(name = "data_transacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTransacao;

    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    private Filial filial;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // Método para definir a data de criação automaticamente antes de persistir
    @PrePersist
    protected void onCreate() {
        validateAgendamentoOuInventario();
        createdAt = new Date();
        dataTransacao = new Date();
    }

    // Método para definir a data de atualização automaticamente antes de atualizar
    @PreUpdate
    protected void onUpdate() {
        validateAgendamentoOuInventario();
        updatedAt = new Date();
    }

    // Validação personalizada para garantir que um dos campos seja preenchido
    private void validateAgendamentoOuInventario() {
        if (agendamento == null && inventario == null) {
            throw new IllegalStateException("Ou o agendamento ou o inventario deve ser fornecido.");
        }
    }

    public enum MetodoPagamento {
        DINHEIRO, CARTAO_CREDITO, CARTAO_DEBITO, PAGAMENTO_ONLINE
    }
}
