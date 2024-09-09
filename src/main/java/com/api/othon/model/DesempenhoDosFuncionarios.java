package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "desempenho_dos_funcionarios")
public class DesempenhoDosFuncionarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional; // Supondo que exista uma entidade Profissional

    @Column(name = "tipo_metrica", nullable = false, length = 100)
    private String tipoMetrica; // Exemplo: "Agendamentos Concluídos", "Avaliação Média"

    @Column(name = "valor_metrica", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorMetrica; // Valor da métrica calculada

    @Column(name = "data_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dataRegistro;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
