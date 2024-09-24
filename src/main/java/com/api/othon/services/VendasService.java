package com.api.othon.services;

import com.api.othon.controller.DTO.VendaItemDTO;
import com.api.othon.model.VendaDTO;
import com.api.othon.model.Vendas;
import com.api.othon.model.repository.VendasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public List<VendaDTO> findAllWithItens() {
        List<VendaDTO> vendaList = vendasRepository.findAllWithItens();
        Map<Long, VendaDTO> vendaMap = new HashMap<>();

        for (VendaDTO venda : vendaList) {
            if (!vendaMap.containsKey(venda.getVendaId())) {
                // Adiciona a venda ao mapa
                vendaMap.put(venda.getVendaId(), venda);
            } else {
                // Agrupa os itens
                VendaDTO existingVenda = vendaMap.get(venda.getVendaId());
                existingVenda.getItens().add(new VendaItemDTO(venda.getVendaItemId(), venda.getVendaItemQuantidade()));
            }
        }

        return new ArrayList<>(vendaMap.values());
    }
    public List<VendaDTO> findAllWithItensFromToday() {
        List<VendaDTO> vendaList = vendasRepository.findAllWithItens();
        Map<Long, VendaDTO> vendaMap = new HashMap<>();
        
        // Obtém a data de hoje
        LocalDate today = LocalDate.now();
    
        for (VendaDTO venda : vendaList) {
            // Converte a data da venda para LocalDate
            LocalDate dataVenda = venda.getDataVenda().toLocalDate(); // Assumindo que 'getDataVenda()' retorna um objeto do tipo LocalDateTime
    
            // Verifica se a data da venda é a de hoje
            if (dataVenda.equals(today)) {
                if (!vendaMap.containsKey(venda.getVendaId())) {
                    // Adiciona a venda ao mapa
                    vendaMap.put(venda.getVendaId(), venda);
                } else {
                    // Agrupa os itens
                    VendaDTO existingVenda = vendaMap.get(venda.getVendaId());
                    existingVenda.getItens().add(new VendaItemDTO(venda.getVendaItemId(), venda.getVendaItemQuantidade()));
                }
            }
        }
    
        return new ArrayList<>(vendaMap.values());
    }
    
}
