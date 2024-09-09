package com.api.othon.services;

import com.api.othon.model.CartaoPresente;
import com.api.othon.model.repository.CartaoPresenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartaoPresenteService {

    private final CartaoPresenteRepository cartaoPresenteRepository;

    @Autowired
    public CartaoPresenteService(CartaoPresenteRepository cartaoPresenteRepository) {
        this.cartaoPresenteRepository = cartaoPresenteRepository;
    }

    public List<CartaoPresente> listarTodos() {
        return cartaoPresenteRepository.findAll();
    }

    public Optional<CartaoPresente> buscarPorId(Long id) {
        return cartaoPresenteRepository.findById(id);
    }

    public CartaoPresente buscarPorCodigo(String codigo) {
        return cartaoPresenteRepository.findByCodigo(codigo);
    }

    public CartaoPresente salvar(CartaoPresente cartaoPresente) {
        return cartaoPresenteRepository.save(cartaoPresente);
    }

    public CartaoPresente atualizar(Long id, CartaoPresente cartaoAtualizado) {
        CartaoPresente cartaoExistente = cartaoPresenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartão presente não encontrado"));

        cartaoExistente.setCodigo(cartaoAtualizado.getCodigo());
        cartaoExistente.setValor(cartaoAtualizado.getValor());
        cartaoExistente.setEmitidoParaCliente(cartaoAtualizado.getEmitidoParaCliente());
        cartaoExistente.setResgatadoPorCliente(cartaoAtualizado.getResgatadoPorCliente());
        cartaoExistente.setStatus(cartaoAtualizado.getStatus());
        cartaoExistente.setDataEmissao(cartaoAtualizado.getDataEmissao());
        cartaoExistente.setDataExpiracao(cartaoAtualizado.getDataExpiracao());
        cartaoExistente.setDataResgate(cartaoAtualizado.getDataResgate());
        cartaoExistente.setUpdatedAt(new Date());

        return cartaoPresenteRepository.save(cartaoExistente);
    }

    public void deletar(Long id) {
        cartaoPresenteRepository.deleteById(id);
    }
}
