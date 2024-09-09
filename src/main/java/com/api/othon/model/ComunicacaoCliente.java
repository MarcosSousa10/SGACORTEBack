package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "comunicacao_clientes")
public class ComunicacaoCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "data_contato", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataContato;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contato", nullable = false)
    private TipoContato tipoContato;

    @Column(name = "assunto", length = 100)
    private String assunto;

    @Column(columnDefinition = "TEXT")
    private String notas;

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
    
    public enum TipoContato {
        Telefone,
        Email,
        SMS,
        Presencial,
        Outro
    }
}
