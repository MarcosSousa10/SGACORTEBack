package com.api.othon.services;

import com.api.othon.controller.dto.AgendamentoDTO;
import com.api.othon.controller.dto.ClienteDTO;
import com.api.othon.controller.dto.FilialDTO;
import com.api.othon.controller.dto.Profissional1DTO;
import com.api.othon.controller.dto.ProfissionalDTO;
import com.api.othon.controller.dto.ServicoDTO;
import com.api.othon.model.Agendamento;
import com.api.othon.model.Agendamento.Status;
import com.api.othon.model.repository.AgendamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Agendamento> listarTodo1s() {
        return agendamentoRepository.findAll();
    }

    public List<AgendamentoDTO> listarTodos() {
        return agendamentoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

   private AgendamentoDTO convertToDTO(Agendamento agendamento) {
    AgendamentoDTO dto = new AgendamentoDTO();
    dto.setId(agendamento.getId());
    dto.setDataHoraAgendamento(agendamento.getDataHoraAgendamento());
    dto.setStatus(agendamento.getStatus());
    dto.setPrecoTotal(agendamento.getPrecoTotal());
    dto.setNotas(agendamento.getNotas());

    // Convertendo Cliente para ClienteDTO
    ClienteDTO clienteDTO = new ClienteDTO();
    clienteDTO.setId(agendamento.getCliente().getId());
    clienteDTO.setNome(agendamento.getCliente().getNome());
    clienteDTO.setEmail(agendamento.getCliente().getEmail());
    clienteDTO.setTelefone(agendamento.getCliente().getTelefone());
    dto.setCliente(clienteDTO);

    // Convertendo Profissional para Profissional1DTO
    Profissional1DTO profissionalDTO = new Profissional1DTO();
    profissionalDTO.setId(agendamento.getProfissional().getId());
    profissionalDTO.setNome(agendamento.getProfissional().getNome());
    profissionalDTO.setEmail(agendamento.getProfissional().getEmail());
    profissionalDTO.setTelefone(agendamento.getProfissional().getTelefone());
    profissionalDTO.setEspecialidade(agendamento.getProfissional().getEspecialidade());
    profissionalDTO.setTaxaComissao(agendamento.getProfissional().getTaxaComissao());
    dto.setProfissional(profissionalDTO);

    // Convertendo Servico para ServicoDTO
    ServicoDTO servicoDTO = new ServicoDTO();
    servicoDTO.setId(agendamento.getServico().getId());
    servicoDTO.setNome(agendamento.getServico().getNome());
    servicoDTO.setPreco(agendamento.getServico().getPreco());
    dto.setServico(servicoDTO);

    // Convertendo Filial para FilialDTO
    FilialDTO filialDTO = new FilialDTO();
    filialDTO.setFilialId(agendamento.getFilial().getFilialId());
    filialDTO.setNome(agendamento.getFilial().getNome());
    filialDTO.setEndereco(agendamento.getFilial().getEndereco());
    dto.setFilial(filialDTO);

    return dto;
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
