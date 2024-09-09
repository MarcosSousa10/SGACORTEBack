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
@Table(name = "campanhas_de_marketing")
public class CampanhaDeMarketing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;

    @Column(name = "orçamento", precision = 10, scale = 2)
    private BigDecimal orçamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCampanha status;

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
        createdAt = new Date();
    }

    // Método para definir a data de atualização automaticamente antes de atualizar
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public enum StatusCampanha {
        ATIVA,
        CONCLUIDA,
        CANCELADA
    }
}
