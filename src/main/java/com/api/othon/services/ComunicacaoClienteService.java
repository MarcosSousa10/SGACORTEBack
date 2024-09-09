package com.api.othon.services;

import com.api.othon.model.ComunicacaoCliente;
import com.api.othon.model.repository.ComunicacaoClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComunicacaoClienteService {

    private final ComunicacaoClienteRepository comunicacaoClienteRepository;

    @Autowired
    public ComunicacaoClienteService(ComunicacaoClienteRepository comunicacaoClienteRepository) {
        this.comunicacaoClienteRepository = comunicacaoClienteRepository;
    }

    public List<ComunicacaoCliente> listarTodos() {
        return comunicacaoClienteRepository.findAll();
    }

    public Optional<ComunicacaoCliente> buscarPorId(Long id) {
        return comunicacaoClienteRepository.findById(id);
    }

    public ComunicacaoCliente salvar(ComunicacaoCliente comunicacaoCliente) {
        return comunicacaoClienteRepository.save(comunicacaoCliente);
    }

    public ComunicacaoCliente atualizar(Long id, ComunicacaoCliente comunicacaoClienteAtualizado) {
        ComunicacaoCliente comunicacaoClienteExistente = comunicacaoClienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comunicação não encontrada"));

        comunicacaoClienteExistente.setCliente(comunicacaoClienteAtualizado.getCliente());
        comunicacaoClienteExistente.setDataContato(comunicacaoClienteAtualizado.getDataContato());
        comunicacaoClienteExistente.setTipoContato(comunicacaoClienteAtualizado.getTipoContato());
        comunicacaoClienteExistente.setAssunto(comunicacaoClienteAtualizado.getAssunto());
        comunicacaoClienteExistente.setNotas(comunicacaoClienteAtualizado.getNotas());
        comunicacaoClienteExistente.setFilial(comunicacaoClienteAtualizado.getFilial());
        comunicacaoClienteExistente.setUpdatedAt(new Date());

        return comunicacaoClienteRepository.save(comunicacaoClienteExistente);
    }

    public void deletar(Long id) {
        comunicacaoClienteRepository.deleteById(id);
    }
}
