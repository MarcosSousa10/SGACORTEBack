package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "transacao_inventario")
public class TransacaoInventario implements Serializable {

    @EmbeddedId
    private TransacaoInventarioId id = new TransacaoInventarioId();

    @ManyToOne
    @MapsId("transacaoId")
    @JoinColumn(name = "transacao_id")
    private Transacao transacao;

    @ManyToOne
    @MapsId("inventarioId")
    @JoinColumn(name = "inventario_id")
    private Inventario inventario;

    // Outros campos adicionais se necessário, como quantidade
}
