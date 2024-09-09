package com.api.othon.services;

import com.api.othon.model.ContasAPagar;
import com.api.othon.model.repository.ContasAPagarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContasAPagarService {

    private final ContasAPagarRepository contasAPagarRepository;

    @Autowired
    public ContasAPagarService(ContasAPagarRepository contasAPagarRepository) {
        this.contasAPagarRepository = contasAPagarRepository;
    }

    public List<ContasAPagar> listarTodos() {
        return contasAPagarRepository.findAll();
    }

    public Optional<ContasAPagar> buscarPorId(Long id) {
        return contasAPagarRepository.findById(id);
    }

    public ContasAPagar salvar(ContasAPagar contasAPagar) {
        return contasAPagarRepository.save(contasAPagar);
    }

    public ContasAPagar atualizar(Long id, ContasAPagar contasAPagarAtualizado) {
        ContasAPagar contasAPagarExistente = contasAPagarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        contasAPagarExistente.setNomeFornecedor(contasAPagarAtualizado.getNomeFornecedor());
        contasAPagarExistente.setDescricao(contasAPagarAtualizado.getDescricao());
        contasAPagarExistente.setValor(contasAPagarAtualizado.getValor());
        contasAPagarExistente.setDataVencimento(contasAPagarAtualizado.getDataVencimento());
        contasAPagarExistente.setDataPagamento(contasAPagarAtualizado.getDataPagamento());
        contasAPagarExistente.setStatus(contasAPagarAtualizado.getStatus());
        contasAPagarExistente.setFilial(contasAPagarAtualizado.getFilial());
        contasAPagarExistente.setUpdatedAt(new Date());

        return contasAPagarRepository.save(contasAPagarExistente);
    }

    public void deletar(Long id) {
        contasAPagarRepository.deleteById(id);
    }
}
