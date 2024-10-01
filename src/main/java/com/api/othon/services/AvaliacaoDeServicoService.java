package com.api.othon.services;

import com.api.othon.controller.dto.AgendamentoDTO;
import com.api.othon.controller.dto.AvaliacaoDTO;
import com.api.othon.controller.dto.ClienteDTO;
import com.api.othon.controller.dto.FilialDTO;
import com.api.othon.controller.dto.Profissional1DTO;
import com.api.othon.controller.dto.ServicoDTO;
import com.api.othon.model.Agendamento;
import com.api.othon.model.AvaliacaoDeServico;
import com.api.othon.model.CampanhaDeMarketing;
import com.api.othon.model.Cliente;
import com.api.othon.model.Filial;
import com.api.othon.model.Profissional;
import com.api.othon.model.Servico;
import com.api.othon.model.repository.AvaliacaoDeServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoDeServicoService {

    @Autowired
    private AvaliacaoDeServicoRepository repository;

    public AvaliacaoDeServico save(AvaliacaoDeServico avaliacao) {
        return repository.save(avaliacao);
    }

    public List<AvaliacaoDTO> listarTodasAvaliacoes() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AvaliacaoDTO convertToDTO(AvaliacaoDeServico avaliacao) {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setId(avaliacao.getId());

        // Se o tipo for LocalDateTime
        dto.setCreated_at(avaliacao.getCreatedAt());

        dto.setComentario(avaliacao.getComentario());
        dto.setNotas(avaliacao.getNota());

        Cliente cliente = avaliacao.getCliente();
        if (cliente != null) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(cliente.getId());
            clienteDTO.setNome(cliente.getNome());
            clienteDTO.setEmail(cliente.getEmail());
            clienteDTO.setTelefone(cliente.getTelefone());
            dto.setCliente(clienteDTO);
        }

        Agendamento agendamento = avaliacao.getAgendamento();
        if (agendamento != null) {
            AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
            agendamentoDTO.setId(agendamento.getId());
            agendamentoDTO.setDataHoraAgendamento(agendamento.getDataHoraAgendamento());
            agendamentoDTO.setStatus(agendamento.getStatus());
            agendamentoDTO.setPrecoTotal(agendamento.getPrecoTotal());
            agendamentoDTO.setNotas(agendamento.getNotas());

            dto.setAgendamento(agendamentoDTO);
        }
        return dto;
    }

    public List<AvaliacaoDeServico> listarTodos1() {
        return repository.findAll();
    }

    public Optional<AvaliacaoDeServico> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
