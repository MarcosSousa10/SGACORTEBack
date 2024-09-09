package com.api.othon.services;

import com.api.othon.model.Fornecedor;
import com.api.othon.model.repository.FornecedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizar(Long id, Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedorExistente = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        fornecedorExistente.setNomeFornecedor(fornecedorAtualizado.getNomeFornecedor());
        fornecedorExistente.setContato(fornecedorAtualizado.getContato());
        fornecedorExistente.setTelefone(fornecedorAtualizado.getTelefone());
        fornecedorExistente.setEmail(fornecedorAtualizado.getEmail());
        fornecedorExistente.setEndereco(fornecedorAtualizado.getEndereco());
        fornecedorExistente.setNotas(fornecedorAtualizado.getNotas());
        fornecedorExistente.setUpdatedAt(new Date());

        return fornecedorRepository.save(fornecedorExistente);
    }

    public void deletar(Long id) {
        fornecedorRepository.deleteById(id);
    }
}
