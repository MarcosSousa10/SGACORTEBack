package com.api.othon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.othon.model.Comissao;
import com.api.othon.model.repository.ComissaoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


@Service
public class ComissaoService {

    @Autowired
    private ComissaoRepository comissaoRepository;

    // Método para listar todas as comissões
    public List<Comissao> listarTodas() {
        return comissaoRepository.findAll();
    }

    // Método para buscar uma comissão pelo ID
    public Optional<Comissao> buscarPorId(Long id) {
        return comissaoRepository.findById(id);
    }

    public List<Comissao> buscarPorProfissionalId(Long profissionalId) {
        return comissaoRepository.findByProfissionalId(profissionalId);
    }

    // Método para calcular e salvar uma nova comissão
    public Comissao salvar(Comissao comissao) {
        comissao.setValorComissao(calcularValorComissao(comissao.getTaxaComissao(),comissao.getValorComissao()));
        return comissaoRepository.save(comissao);
    }

    // Método para deletar uma comissão
    public void deletar(Long id) {
        comissaoRepository.deleteById(id);
    }

    // Método para atualizar uma comissão
    public Comissao atualizar(Long id, Comissao comissaoAtualizada) {
        Comissao comissaoExistente = comissaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comissão não encontrada"));

        comissaoExistente.setProfissionalId(comissaoAtualizada.getProfissionalId());
        comissaoExistente.setAgendamentoId(comissaoAtualizada.getAgendamentoId());
        comissaoExistente.setTaxaComissao(comissaoAtualizada.getTaxaComissao());
        comissaoExistente.setValorComissao(calcularValorComissao(comissaoAtualizada.getTaxaComissao(),comissaoAtualizada.getValorComissao()));
        comissaoExistente.setUpdatedAt(LocalDateTime.now());

        return comissaoRepository.save(comissaoExistente);
    }

    // Método para calcular o valor da comissão
    private BigDecimal calcularValorComissao(BigDecimal taxaComissao,BigDecimal precoServico) {
        // Exemplo de cálculo (isso precisaria ser ajustado com base nos dados reais)
      //  BigDecimal precoServico = new BigDecimal("100.00"); // Este valor deve ser ajustado conforme necessário
        return precoServico.multiply(taxaComissao).divide(new BigDecimal("100"));
    }
}
