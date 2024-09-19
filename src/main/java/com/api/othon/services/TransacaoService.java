package com.api.othon.services;

import com.api.othon.model.Transacao;
import com.api.othon.model.repository.TransacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    @Autowired
    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public List<Transacao> listarTodas() {
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> buscarPorId(Long id) {
        return transacaoRepository.findById(id);
    }

    public Transacao salvar(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public Transacao atualizar(Long id, Transacao transacaoAtualizada) {
        Transacao transacaoExistente = transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        transacaoExistente.setAgendamento(transacaoAtualizada.getAgendamento());
        transacaoExistente.setInventarios(transacaoAtualizada.getInventarios());
        transacaoExistente.setMetodoPagamento(transacaoAtualizada.getMetodoPagamento());
        transacaoExistente.setValorPago(transacaoAtualizada.getValorPago());
        transacaoExistente.setDataTransacao(transacaoAtualizada.getDataTransacao());
        transacaoExistente.setFilial(transacaoAtualizada.getFilial());
        transacaoExistente.setUpdatedAt(new Date());

        return transacaoRepository.save(transacaoExistente);
    }

    public void deletar(Long id) {
        transacaoRepository.deleteById(id);
    }
}
