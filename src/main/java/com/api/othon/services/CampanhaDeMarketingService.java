package com.api.othon.services;

import com.api.othon.model.CampanhaDeMarketing;
import com.api.othon.model.repository.CampanhaDeMarketingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CampanhaDeMarketingService {

    private final CampanhaDeMarketingRepository  campanhaDeMarketingRepository;

    @Autowired
    public CampanhaDeMarketingService(CampanhaDeMarketingRepository campanhaDeMarketingRepository) {
        this.campanhaDeMarketingRepository = campanhaDeMarketingRepository;
    }

    public List<CampanhaDeMarketing> listarTodos() {
        return campanhaDeMarketingRepository.findAll();
    }

    public Optional<CampanhaDeMarketing> buscarPorId(Long id) {
        return campanhaDeMarketingRepository.findById(id);
    }

    public CampanhaDeMarketing salvar(CampanhaDeMarketing campanhaDeMarketing) {
        return campanhaDeMarketingRepository.save(campanhaDeMarketing);
    }

    public CampanhaDeMarketing atualizar(Long id, CampanhaDeMarketing campanhaDeMarketingAtualizada) {
        CampanhaDeMarketing campanhaExistente = campanhaDeMarketingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada"));

        campanhaExistente.setNome(campanhaDeMarketingAtualizada.getNome());
        campanhaExistente.setDescricao(campanhaDeMarketingAtualizada.getDescricao());
        campanhaExistente.setDataInicio(campanhaDeMarketingAtualizada.getDataInicio());
        campanhaExistente.setDataFim(campanhaDeMarketingAtualizada.getDataFim());
        campanhaExistente.setOrçamento(campanhaDeMarketingAtualizada.getOrçamento());
        campanhaExistente.setStatus(campanhaDeMarketingAtualizada.getStatus());
        campanhaExistente.setFilial(campanhaDeMarketingAtualizada.getFilial());
        campanhaExistente.setUpdatedAt(new Date());

        return campanhaDeMarketingRepository.save(campanhaExistente);
    }

    public void deletar(Long id) {
        campanhaDeMarketingRepository.deleteById(id);
    }
}
