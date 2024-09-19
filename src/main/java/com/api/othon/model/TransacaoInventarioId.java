package com.api.othon.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class TransacaoInventarioId implements Serializable {

    private Long transacaoId;
    private Long inventarioId;

}