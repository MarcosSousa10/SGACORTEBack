package com.api.othon.services;

import com.api.othon.model.Cliente;
import com.api.othon.model.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os clientes
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Buscar um cliente pelo ID
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Salvar um novo cliente
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Atualizar um cliente existente
    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
        clienteExistente.setDataNascimento(clienteAtualizado.getDataNascimento());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());
        clienteExistente.setUpdatedAt(new Date());

        return clienteRepository.save(clienteExistente);
    }

    // Deletar um cliente pelo ID
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
    public Optional<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNome(nome);
    }
}
