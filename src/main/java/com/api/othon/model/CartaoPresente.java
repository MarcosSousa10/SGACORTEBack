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
@Table(name = "cartoes_presente")
public class CartaoPresente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true, length = 100)
    private String codigo;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "emitido_para_cliente_id", nullable = true)
    private Cliente emitidoParaCliente;

    @ManyToOne
    @JoinColumn(name = "resgatado_por_cliente_id", nullable = true)
    private Cliente resgatadoPorCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusCartao status;

    @Column(name = "data_emissao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;

    @Column(name = "data_expiracao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataExpiracao;

    @Column(name = "data_resgate")
    @Temporal(TemporalType.DATE)
    private Date dataResgate;

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

    public enum StatusCartao {
        ATIVO, RESGATADO, EXPIRADO
    }
}
