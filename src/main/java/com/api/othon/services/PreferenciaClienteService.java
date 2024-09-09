package com.api.othon.services;

import com.api.othon.model.PreferenciaCliente;
import com.api.othon.model.repository.PreferenciaClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class PreferenciaClienteService {

    private final PreferenciaClienteRepository preferenciaClienteRepository;

    @Autowired
    public PreferenciaClienteService(PreferenciaClienteRepository preferenciaClienteRepository) {
        this.preferenciaClienteRepository = preferenciaClienteRepository;
    }

    public List<PreferenciaCliente> listarTodas() {
        return preferenciaClienteRepository.findAll();
    }

    public Optional<PreferenciaCliente> buscarPorId(Long id) {
        return preferenciaClienteRepository.findById(id);
    }

    public PreferenciaCliente salvar(PreferenciaCliente preferenciaCliente) {
        return preferenciaClienteRepository.save(preferenciaCliente);
    }

    public PreferenciaCliente atualizar(Long id, PreferenciaCliente preferenciaAtualizada) {
        PreferenciaCliente preferenciaExistente = preferenciaClienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Preferência não encontrada"));

        preferenciaExistente.setCliente(preferenciaAtualizada.getCliente());
        preferenciaExistente.setTipoPreferencia(preferenciaAtualizada.getTipoPreferencia());
        preferenciaExistente.setValorPreferencia(preferenciaAtualizada.getValorPreferencia());
        preferenciaExistente.setUpdatedAt(LocalDateTime.now());

        return preferenciaClienteRepository.save(preferenciaExistente);
    }

    public void deletar(Long id) {
        preferenciaClienteRepository.deleteById(id);
    }
}
