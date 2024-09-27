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
    private String nome; 

    @Column(nullable = false, length = 20, unique = true)
    private String ct; 

    @Column(length = 255)
    private String endereco;

    @Column(nullable = false, length = 20)
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "imagem_id", referencedColumnName = "id")
    private ImageFiliais imagem;
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
