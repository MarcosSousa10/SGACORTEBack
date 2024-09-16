package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "filiais")
public class Filial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filial_id")
    private Long filialId;

    @Column(nullable = false, length = 100)
    private String nome; // Nome da filial

    @Column(nullable = false, length = 20, unique = true)
    private String ct; // Código único da filial (ex.: código de identificação)

    @Column(length = 255)
    private String endereco; // Endereço da filial

    @Column(nullable = false, length = 20)
    private String telefone; // Telefone da filial

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
}
