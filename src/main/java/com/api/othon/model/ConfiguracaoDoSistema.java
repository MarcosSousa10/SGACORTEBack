package com.api.othon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "configuracoes_do_sistema")
public class ConfiguracaoDoSistema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chave_configuracao", nullable = false, length = 100, unique = true)
    private String chaveConfiguracao;

    @Column(name = "valor_configuracao", nullable = false, columnDefinition = "TEXT")
    private String valorConfiguracao;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "ultima_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp ultimaAtualizacao;

    @PrePersist
    protected void onCreate() {
        ultimaAtualizacao = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        ultimaAtualizacao = new Timestamp(System.currentTimeMillis());
    }
}
