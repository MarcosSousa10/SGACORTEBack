package com.api.othon.services;

import com.api.othon.model.Vendas;
import com.api.othon.model.repository.VendasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendasService {

    private final VendasRepository vendasRepository;

    @Autowired
    public VendasService(VendasRepository vendasRepository) {
        this.vendasRepository = vendasRepository;
    }

    public Vendas salvar(Vendas vendas) {
        return vendasRepository.save(vendas);
    }

    public Optional<Vendas> buscarPorId(Long id) {
        return vendasRepository.findById(id);
    }

    public void deletar(Long id) {
        vendasRepository.deleteById(id);
    }

    public Vendas atualizar(Long id, Vendas vendasAtualizado) {
        if (vendasRepository.existsById(id)) {
            vendasAtualizado.setId(id);
            return vendasRepository.save(vendasAtualizado);
        } else {
            throw new RuntimeException("Venda não encontrada");
        }
    }
    public List<Vendas> listarTodos() {
        return vendasRepository.findAll();
    }

}
