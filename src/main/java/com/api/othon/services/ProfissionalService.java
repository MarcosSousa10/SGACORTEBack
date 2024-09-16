package com.api.othon.services;

import com.api.othon.model.Profissional;
import com.api.othon.model.repository.ProfissionalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    @Autowired
    public ProfissionalService(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }
    public Optional<Profissional> buscarPorNome(String nome) {
        return profissionalRepository.findByNome(nome);
    }
    public List<Profissional> listarTodos() {
        return profissionalRepository.findAll();
    }

    public Optional<Profissional> buscarPorId(Long id) {
        return profissionalRepository.findById(id);
    }

    public Profissional salvar(Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

    public Profissional atualizar(Long id, Profissional profissionalAtualizado) {
        Profissional profissionalExistente = profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        profissionalExistente.setNome(profissionalAtualizado.getNome());
        profissionalExistente.setEmail(profissionalAtualizado.getEmail());
        profissionalExistente.setTelefone(profissionalAtualizado.getTelefone());
        profissionalExistente.setEspecialidade(profissionalAtualizado.getEspecialidade());
        profissionalExistente.setTaxaComissao(profissionalAtualizado.getTaxaComissao());
        profissionalExistente.setFilial(profissionalAtualizado.getFilial());
        profissionalExistente.setDisponibilidade(profissionalAtualizado.getDisponibilidade());
        profissionalExistente.setUpdatedAt(new java.util.Date());

        return profissionalRepository.save(profissionalExistente);
    }

    public void deletar(Long id) {
        profissionalRepository.deleteById(id);
    }
}
