package com.api.othon.services;

import com.api.othon.model.Image;
import com.api.othon.model.Profissional;
import com.api.othon.model.Servico;
import com.api.othon.model.imagemService;
import com.api.othon.model.repository.ImageRepository;
import com.api.othon.model.repository.ImageServiceRepository;
import com.api.othon.model.repository.ServicoRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {
    @Autowired
    private final ServicoRepository servicoRepository;
    @Autowired
    private ImageServiceRepository imageRepository;

    @Autowired
    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarPorId(Long id) {
        return servicoRepository.findById(id);
    }

    public Servico salvar1(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Transactional // Garante que todas as operações sejam tratadas como uma transação
    public Servico salvar(Servico servico, MultipartFile imagem) throws IOException {
        // Valida se a imagem não é nula ou vazia
        if (imagem == null || imagem.isEmpty()) {
            throw new IllegalArgumentException("A imagem não pode ser nula ou vazia");
        }

        imagemService img = new imagemService();
        img.setData(imagem.getBytes()); // Convertendo a imagem para bytes
        imagemService imagemSalva = imageRepository.save(img); // Salvar a imagem no repositório

        // Associar a imagem ao profissional
        servico.setImagem(imagemSalva);

        // Salvar o profissional
        return servicoRepository.save(servico);
    }

    public Servico atualizar(Long id, Servico servicoAtualizado) {
        Servico servicoExistente = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        servicoExistente.setNome(servicoAtualizado.getNome());
        servicoExistente.setDescricao(servicoAtualizado.getDescricao());
        servicoExistente.setPreco(servicoAtualizado.getPreco());
        servicoExistente.setDuracao(servicoAtualizado.getDuracao());
        servicoExistente.setFilial(servicoAtualizado.getFilial());
        servicoExistente.setUpdatedAt(new java.util.Date());

        return servicoRepository.save(servicoExistente);
    }

    public void deletar(Long id) {
        servicoRepository.deleteById(id);
    }
}
