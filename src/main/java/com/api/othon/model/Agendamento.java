package com.api.othon.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "agendamentos")
public class Agendamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora_agendamento", nullable = false)
    private LocalDateTime  dataHoraAgendamento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "preco_total", precision = 10, scale = 2)
    private BigDecimal precoTotal;

    @Column(columnDefinition = "TEXT")
    private String notas;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    private Filial filial;

    // Enum para status do agendamento
    public enum Status {
        AGENDADO, CONCLUIDO, CANCELADO
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new java.util.Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new java.util.Date();
    }
}
