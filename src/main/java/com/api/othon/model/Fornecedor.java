package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "fornecedores")
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_fornecedor", nullable = false, length = 100)
    private String nomeFornecedor;

    @Column(name = "contato", length = 100)
    private String contato;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "endereco", length = 255)
    private String endereco;

    @Column(columnDefinition = "TEXT")
    private String notas;

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
