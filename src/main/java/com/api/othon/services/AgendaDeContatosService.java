package com.api.othon.services;

import com.api.othon.model.AgendaDeContatos;
import com.api.othon.model.repository.AgendaDeContatosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaDeContatosService {

    private final AgendaDeContatosRepository agendaDeContatosRepository;

    @Autowired
    public AgendaDeContatosService(AgendaDeContatosRepository agendaDeContatosRepository) {
        this.agendaDeContatosRepository = agendaDeContatosRepository;
    }

    public List<AgendaDeContatos> listarTodos() {
        return agendaDeContatosRepository.findAll();
    }

    public Optional<AgendaDeContatos> buscarPorId(Long id) {
        return agendaDeContatosRepository.findById(id);
    }

    public AgendaDeContatos salvar(AgendaDeContatos agendaDeContatos) {
        return agendaDeContatosRepository.save(agendaDeContatos);
    }

    public AgendaDeContatos atualizar(Long id, AgendaDeContatos agendaDeContatosAtualizado) {
        AgendaDeContatos agendaExistente = agendaDeContatosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));

        agendaExistente.setNome(agendaDeContatosAtualizado.getNome());
        agendaExistente.setDescricao(agendaDeContatosAtualizado.getDescricao());
        agendaExistente.setCriterios(agendaDeContatosAtualizado.getCriterios());
        agendaExistente.setUpdatedAt(new Date());

        return agendaDeContatosRepository.save(agendaExistente);
    }

    public void deletar(Long id) {
        agendaDeContatosRepository.deleteById(id);
    }
}
