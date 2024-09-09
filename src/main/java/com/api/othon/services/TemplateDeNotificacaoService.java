package com.api.othon.services;

import com.api.othon.model.TemplateDeNotificacao;
import com.api.othon.model.repository.TemplateDeNotificacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TemplateDeNotificacaoService {

    private final TemplateDeNotificacaoRepository templateDeNotificacaoRepository;

    @Autowired
    public TemplateDeNotificacaoService(TemplateDeNotificacaoRepository templateDeNotificacaoRepository) {
        this.templateDeNotificacaoRepository = templateDeNotificacaoRepository;
    }

    public List<TemplateDeNotificacao> listarTodos() {
        return templateDeNotificacaoRepository.findAll();
    }

    public Optional<TemplateDeNotificacao> buscarPorId(Long id) {
        return templateDeNotificacaoRepository.findById(id);
    }

    public TemplateDeNotificacao salvar(TemplateDeNotificacao templateDeNotificacao) {
        return templateDeNotificacaoRepository.save(templateDeNotificacao);
    }

    public TemplateDeNotificacao atualizar(Long id, TemplateDeNotificacao templateAtualizado) {
        TemplateDeNotificacao templateExistente = templateDeNotificacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template não encontrado"));

        templateExistente.setNomeTemplate(templateAtualizado.getNomeTemplate());
        templateExistente.setConteudoTemplate(templateAtualizado.getConteudoTemplate());
        templateExistente.setTipoTemplate(templateAtualizado.getTipoTemplate());
        templateExistente.setUpdatedAt(new Date());

        return templateDeNotificacaoRepository.save(templateExistente);
    }

    public void deletar(Long id) {
        templateDeNotificacaoRepository.deleteById(id);
    }
}
