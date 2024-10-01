
package com.api.othon.controller.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.api.othon.model.Cliente;
import com.api.othon.model.Filial;
import com.api.othon.model.Servico;
import com.api.othon.model.Agendamento.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoDTO {
    private Long id;
    private Timestamp created_at;
    private String comentario;
    private Integer notas;
    private ClienteDTO cliente;
    private AgendamentoDTO agendamento;
}

