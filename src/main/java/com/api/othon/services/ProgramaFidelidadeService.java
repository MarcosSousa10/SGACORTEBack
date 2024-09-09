package com.api.othon.services;

import com.api.othon.model.ProgramaFidelidade;
import com.api.othon.model.repository.ProgramaFidelidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramaFidelidadeService {

    private final ProgramaFidelidadeRepository programaFidelidadeRepository;

    @Autowired
    public ProgramaFidelidadeService(ProgramaFidelidadeRepository programaFidelidadeRepository) {
        this.programaFidelidadeRepository = programaFidelidadeRepository;
    }

    public List<ProgramaFidelidade> listarTodos() {
        return programaFidelidadeRepository.findAll();
    }

    public Optional<ProgramaFidelidade> buscarPorId(Long id) {
        return programaFidelidadeRepository.findById(id);
    }

    public ProgramaFidelidade salvar(ProgramaFidelidade programaFidelidade) {
        return programaFidelidadeRepository.save(programaFidelidade);
    }

    public ProgramaFidelidade atualizar(Long id, ProgramaFidelidade programaAtualizado) {
        ProgramaFidelidade programaExistente = programaFidelidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa de fidelidade não encontrado"));

        programaExistente.setNomeRecompensa(programaAtualizado.getNomeRecompensa());
        programaExistente.setDescricao(programaAtualizado.getDescricao());
        programaExistente.setPontosNecessarios(programaAtualizado.getPontosNecessarios());
        programaExistente.setDisponibilidadeInicio(programaAtualizado.getDisponibilidadeInicio());
        programaExistente.setDisponibilidadeFim(programaAtualizado.getDisponibilidadeFim());
        programaExistente.setFilial(programaAtualizado.getFilial());
        programaExistente.setUpdatedAt(new Date());

        return programaFidelidadeRepository.save(programaExistente);
    }

    public void deletar(Long id) {
        programaFidelidadeRepository.deleteById(id);
    }
}
