package com.api.othon.services;

import com.api.othon.model.TransacaoInventario;
import com.api.othon.model.TransacaoInventarioId;
import com.api.othon.model.repository.TransacaoInventarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoInventarioService {

    @Autowired
    private TransacaoInventarioRepository transacaoInventarioRepository;

    public List<TransacaoInventario> getAllTransacoesInventarios() {
        return transacaoInventarioRepository.findAll();
    }

    public TransacaoInventario saveTransacaoInventario(TransacaoInventario transacaoInventario) {
        return transacaoInventarioRepository.save(transacaoInventario);
    }

    public void deleteTransacaoInventario(Long transacaoId, Long inventarioId) {
        TransacaoInventarioId id = new TransacaoInventarioId();
        id.setTransacaoId(transacaoId);
        id.setInventarioId(inventarioId);
        transacaoInventarioRepository.deleteById(id);
    }

    // Outros métodos como findById ou custom queries
}
