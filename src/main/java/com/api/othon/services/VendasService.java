package com.api.othon.services;

import com.api.othon.model.VendaDTO;
import com.api.othon.model.Vendas;
import com.api.othon.model.repository.VendasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Optional<VendaDTO> buscarPorId(Long id) {
        return vendasRepository.findAllWithItensById(id);
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

    public List<Vendas> listarTodoss() {
        return vendasRepository.findAll();
    }

    public List<Vendas> listarVendasDoDia() {
        LocalDate today = LocalDate.now();
        return vendasRepository.findVendasByDataVenda(today);
    }


    public List<VendaDTO> listarTodos() {
        return vendasRepository.findAllWithItens();
    }
}
