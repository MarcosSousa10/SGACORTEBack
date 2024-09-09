package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "logs_de_auditoria")
public class LogAuditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "acao", nullable = false, length = 100)
    private String acao;

    @Column(name = "detalhes", columnDefinition = "TEXT")
    private String detalhes;

    @Column(name = "endereco_ip", length = 45)
    private String enderecoIp;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora = LocalDateTime.now();

    // Getters e Setters
}
