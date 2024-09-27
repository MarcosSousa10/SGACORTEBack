package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "imageFilais")
public class ImageFiliais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "Filial_id", nullable = true)
    private Filial filial;

    public ImageFiliais() {
    }

    public ImageFiliais(Long id, byte[] data, Filial filial) {
        this.id = id;
        this.data = data;
        this.filial = filial;
    }

    @Override
    public String toString() {
        return "Image [id=" + id + "]";
    }
}
