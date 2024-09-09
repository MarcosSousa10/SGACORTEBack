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
@Table(name = "profissionais")
public class Profissional implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, length = 100)
    private String especialidade;

    @Column(name = "taxa_comissao", precision = 5, scale = 2)
    private BigDecimal taxaComissao;

    @Column(columnDefinition = "TEXT")
    private String disponibilidade;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    private Filial filial;

    // Método que define a data de criação automaticamente antes de persistir
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    // Método que define a data de atualização automaticamente antes de atualizar
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
