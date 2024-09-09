package com.api.othon.services;

import com.api.othon.model.PromocaoAtiva;
import com.api.othon.model.repository.PromocaoAtivaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PromocaoAtivaService {

    private final PromocaoAtivaRepository promocaoAtivaRepository;

    @Autowired
    public PromocaoAtivaService(PromocaoAtivaRepository promocaoAtivaRepository) {
        this.promocaoAtivaRepository = promocaoAtivaRepository;
    }

    public List<PromocaoAtiva> listarTodos() {
        return promocaoAtivaRepository.findAll();
    }

    public Optional<PromocaoAtiva> buscarPorId(Long id) {
        return promocaoAtivaRepository.findById(id);
    }

    public List<PromocaoAtiva> buscarPorFilial(Long filialId) {
        return promocaoAtivaRepository.findByFilial_FilialId(filialId);
    }

    public PromocaoAtiva salvar(PromocaoAtiva promocaoAtiva) {
        return promocaoAtivaRepository.save(promocaoAtiva);
    }

    public PromocaoAtiva atualizar(Long id, PromocaoAtiva promocaoAtualizada) {
        PromocaoAtiva promocaoExistente = promocaoAtivaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promoção não encontrada"));

        promocaoExistente.setNome(promocaoAtualizada.getNome());
        promocaoExistente.setDescricao(promocaoAtualizada.getDescricao());
        promocaoExistente.setDataInicio(promocaoAtualizada.getDataInicio());
        promocaoExistente.setDataFim(promocaoAtualizada.getDataFim());
        promocaoExistente.setDesconto(promocaoAtualizada.getDesconto());
        promocaoExistente.setFilial(promocaoAtualizada.getFilial());
        promocaoExistente.setUpdatedAt(new Date());

        return promocaoAtivaRepository.save(promocaoExistente);
    }

    public void deletar(Long id) {
        promocaoAtivaRepository.deleteById(id);
    }
}
