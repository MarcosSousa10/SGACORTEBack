package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "imageService")
public class imagemService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column 
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = true)
    private Servico servico;

    public imagemService() {
    }


    public imagemService(Long id, byte[] data, Servico servico) {
        this.id = id;
        this.data = data;
        this.servico = servico;
    }


    @Override
    public String toString() {
        return "Image [id=" + id + "]";
    }
}
