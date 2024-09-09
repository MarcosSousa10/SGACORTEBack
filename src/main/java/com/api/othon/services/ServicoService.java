package com.api.othon.services;

import com.api.othon.model.Servico;
import com.api.othon.model.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    @Autowired
    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarPorId(Long id) {
        return servicoRepository.findById(id);
    }

    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Servico atualizar(Long id, Servico servicoAtualizado) {
        Servico servicoExistente = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        servicoExistente.setNome(servicoAtualizado.getNome());
        servicoExistente.setDescricao(servicoAtualizado.getDescricao());
        servicoExistente.setPreco(servicoAtualizado.getPreco());
        servicoExistente.setDuracao(servicoAtualizado.getDuracao());
        servicoExistente.setFilial(servicoAtualizado.getFilial());
        servicoExistente.setUpdatedAt(new java.util.Date());

        return servicoRepository.save(servicoExistente);
    }

    public void deletar(Long id) {
        servicoRepository.deleteById(id);
    }
}
