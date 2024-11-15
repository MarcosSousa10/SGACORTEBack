package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column // Remover o length aqui
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = true)
    private Profissional profissional;

    public Image() {
    }

    public Image(Long id, byte[] data, Profissional profissional) {
        this.id = id;
        this.data = data;
        this.profissional = profissional;
    }

    @Override
    public String toString() {
        return "Image [id=" + id + "]";
    }
}
