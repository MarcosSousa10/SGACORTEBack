package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comissoes")
public class Comissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profissional_id", nullable = false)
    private Long profissionalId;

    @Column(name = "agendamento_id", nullable = false)
    private Long agendamentoId;

    @Column(name = "taxa_comissao", precision = 5, scale = 2, nullable = false)
    private BigDecimal taxaComissao;

    @Column(name = "valor_comissao", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorComissao;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
