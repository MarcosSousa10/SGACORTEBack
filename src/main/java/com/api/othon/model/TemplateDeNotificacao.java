package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "templates_de_notificacoes")
public class TemplateDeNotificacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_template", nullable = false, length = 100)
    private String nomeTemplate;

    @Column(name = "conteudo_template", columnDefinition = "TEXT")
    private String conteudoTemplate;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_template", nullable = false)
    private TipoTemplate tipoTemplate;

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

    public enum TipoTemplate {
        SMS,
        EMAIL,
        NOTIFICACAO_PUSH
    }
}
