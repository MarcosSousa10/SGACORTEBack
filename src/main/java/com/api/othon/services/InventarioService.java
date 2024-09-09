package com.api.othon.services;

import com.api.othon.model.Inventario;
import com.api.othon.model.repository.InventarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    @Autowired
    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<Inventario> listarTodos() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> buscarPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    public Inventario salvar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Inventario atualizar(Long id, Inventario inventarioAtualizado) {
        Inventario inventarioExistente = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        inventarioExistente.setNomeProduto(inventarioAtualizado.getNomeProduto());
        inventarioExistente.setDescricao(inventarioAtualizado.getDescricao());
        inventarioExistente.setQuantidade(inventarioAtualizado.getQuantidade());
        inventarioExistente.setPreco(inventarioAtualizado.getPreco());
        inventarioExistente.setFornecedor(inventarioAtualizado.getFornecedor());
        inventarioExistente.setFilial(inventarioAtualizado.getFilial());
        inventarioExistente.setUpdatedAt(new Date());

        return inventarioRepository.save(inventarioExistente);
    }

    public void deletar(Long id) {
        inventarioRepository.deleteById(id);
    }
}
