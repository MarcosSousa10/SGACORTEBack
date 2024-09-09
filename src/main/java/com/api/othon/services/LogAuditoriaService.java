package com.api.othon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.othon.model.LogAuditoria;
import com.api.othon.model.repository.LogAuditoriaRepository;

@Service
public class LogAuditoriaService {

    private final LogAuditoriaRepository logAuditoriaRepository;

    @Autowired
    public LogAuditoriaService(LogAuditoriaRepository logAuditoriaRepository) {
        this.logAuditoriaRepository = logAuditoriaRepository;
    }

    public void registrarLog(Long usuarioId, String acao, String detalhes, String enderecoIp) {
        LogAuditoria log = new LogAuditoria();
        log.setUsuarioId(usuarioId);
        log.setAcao(acao);
        log.setDetalhes(detalhes);
        log.setEnderecoIp(enderecoIp);
        logAuditoriaRepository.save(log);
    }
}
