package com.api.othon.services;

import com.api.othon.model.Agendamento;
import com.api.othon.model.Agendamento.Status;
import com.api.othon.model.repository.AgendamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public Optional<Agendamento> buscarPorId(Long id) {
        return agendamentoRepository.findById(id);
    }
    public Agendamento atualizarStatus(Agendamento agendamentoAtualizado) {
        return agendamentoRepository.save(agendamentoAtualizado);
    }
    
    public Agendamento buscarPorIdStatus(Long id) {
        return agendamentoRepository.findById(id).orElse(null);
    }
    
    public Agendamento salvar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento atualizar(Long id, Agendamento agendamentoAtualizado) {
        Agendamento agendamentoExistente = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        agendamentoExistente.setCliente(agendamentoAtualizado.getCliente());
        agendamentoExistente.setProfissional(agendamentoAtualizado.getProfissional());
        agendamentoExistente.setServico(agendamentoAtualizado.getServico());
        agendamentoExistente.setDataHoraAgendamento(agendamentoAtualizado.getDataHoraAgendamento());
        agendamentoExistente.setStatus(agendamentoAtualizado.getStatus());
        agendamentoExistente.setPrecoTotal(agendamentoAtualizado.getPrecoTotal());
        agendamentoExistente.setFilial(agendamentoAtualizado.getFilial());
        agendamentoExistente.setNotas(agendamentoAtualizado.getNotas());
        agendamentoExistente.setUpdatedAt(new java.util.Date());

        return agendamentoRepository.save(agendamentoExistente);
    }

    public void deletar(Long id) {
        agendamentoRepository.deleteById(id);
    }
    public List<Agendamento> buscarPorNomeCliente(String nome) {
        return agendamentoRepository.findByClienteNomeContainingIgnoreCase(nome);
    }
    public List<Agendamento> listarAgendados() {
        return agendamentoRepository.findByStatus(Status.AGENDADO);
    }
}
