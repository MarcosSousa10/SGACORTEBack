package com.api.othon.services;

import com.api.othon.controller.dto.ProfissionalDTO;
import com.api.othon.model.Image;
import com.api.othon.model.Profissional;
import com.api.othon.model.repository.ImageRepository;
import com.api.othon.model.repository.ProfissionalRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    public ProfissionalService(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }
    public Optional<Profissional> buscarPorNome(String nome) {
        return profissionalRepository.findByNome(nome);
    }

    public Optional<Profissional> buscarPorId(Long id) {
        return profissionalRepository.findById(id);
    }
 public List<ProfissionalDTO> listarProfissionaisComImagem() {
        return profissionalRepository.findAllProfissionalDTOs();
    }
    public List<Profissional> listarTodos() {
        return profissionalRepository.findAllWithImages();
     }
     
    @Transactional // Garante que todas as operações sejam tratadas como uma transação
    public Profissional salvar(Profissional profissional, MultipartFile imagem) throws IOException {
        // Valida se a imagem não é nula ou vazia
        if (imagem == null || imagem.isEmpty()) {
            throw new IllegalArgumentException("A imagem não pode ser nula ou vazia");
        }

        // Salvar a imagem no banco de dados
        Image img = new Image();
        img.setData(imagem.getBytes());  // Convertendo a imagem para bytes
        Image imagemSalva = imageRepository.save(img);  // Salvar a imagem no repositório

        // Associar a imagem ao profissional
        profissional.setImagem(imagemSalva);

        // Salvar o profissional
        return profissionalRepository.save(profissional);
    }
    public Profissional atualizar(Long id, Profissional profissionalAtualizado) {
        Profissional profissionalExistente = profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        profissionalExistente.setNome(profissionalAtualizado.getNome());
        profissionalExistente.setEmail(profissionalAtualizado.getEmail());
        profissionalExistente.setTelefone(profissionalAtualizado.getTelefone());
        profissionalExistente.setEspecialidade(profissionalAtualizado.getEspecialidade());
        profissionalExistente.setTaxaComissao(profissionalAtualizado.getTaxaComissao());
        profissionalExistente.setFilial(profissionalAtualizado.getFilial());
        profissionalExistente.setDisponibilidade(profissionalAtualizado.getDisponibilidade());
        profissionalExistente.setUpdatedAt(new java.util.Date());

        return profissionalRepository.save(profissionalExistente);
    }

    public void deletar(Long id) {
        profissionalRepository.deleteById(id);
    }
}
