package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "programa_fidelidade")
public class ProgramaFidelidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_recompensa", nullable = false, length = 100)
    private String nomeRecompensa;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "pontos_necessarios", nullable = false)
    private Integer pontosNecessarios;

    @Column(name = "disponibilidade_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date disponibilidadeInicio;

    @Column(name = "disponibilidade_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date disponibilidadeFim;

    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    private Filial filial;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
