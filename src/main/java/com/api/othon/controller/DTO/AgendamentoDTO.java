package com.api.othon.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.api.othon.model.Cliente;
import com.api.othon.model.Filial;
import com.api.othon.model.Servico;
import com.api.othon.model.Agendamento.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoDTO {
    private Long id;
    private LocalDateTime dataHoraAgendamento;
    private Status status;
    private BigDecimal precoTotal;
    private String notas;
    private ClienteDTO cliente;
    private Profissional1DTO profissional;
    private ServicoDTO servico;
    private FilialDTO filial;
}

