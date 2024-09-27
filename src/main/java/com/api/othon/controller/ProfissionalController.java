package com.api.othon.controller;

import com.api.othon.controller.dto.ProfissionalDTO;
import com.api.othon.model.Filial;
import com.api.othon.model.Image;
import com.api.othon.model.Profissional;
import com.api.othon.services.ImageService;
import com.api.othon.services.ProfissionalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {
    @Autowired
    private final ProfissionalService profissionalService;
    @Autowired
    private final ImageService imageService;

    @Autowired
    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
        this.imageService = null;
    }


    @GetMapping
    public ResponseEntity<List<Profissional>> listarTodos() {
        List<Profissional> profissionais = profissionalService.listarTodos();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscarPorId(@PathVariable Long id) {
        Optional<Profissional> profissional = profissionalService.buscarPorId(id);
        return profissional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Profissional> buscarPorNome(@PathVariable String nome) {
        Optional<Profissional> profissional = profissionalService.buscarPorNome(nome);
        return profissional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping(consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public Profissional criars(
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("telefone") String telefone,
            @RequestParam("especialidade") String especialidade,
            @RequestParam("taxaComissao") BigDecimal taxaComissao,
            @RequestParam("disponibilidade") String disponibilidade,
            @RequestParam("filialId") Filial filialId,
            @RequestPart("imagem") MultipartFile imagem) throws IOException {
                System.out.println(nome);
                System.out.println(nome);
                System.out.println(nome);
                System.out.println(nome);
                System.out.println(nome);

        Profissional profissional = new Profissional();
        profissional.setNome(nome);
        profissional.setEmail(email);
        profissional.setTelefone(telefone);
        profissional.setEspecialidade(especialidade);
        profissional.setTaxaComissao(taxaComissao);
        profissional.setDisponibilidade(disponibilidade);
        profissional.setFilial(filialId);
    
        // Salvar o profissional junto com a imagem
        return profissionalService.salvar(profissional, imagem);
    }
        @GetMapping("/com-imagem")
    public ResponseEntity<List<ProfissionalDTO>> listarProfissionaisComImagem() {
        List<ProfissionalDTO> profissionais = profissionalService.listarProfissionaisComImagem();
        return ResponseEntity.ok(profissionais);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Profissional> atualizar(@PathVariable Long id,
            @RequestBody Profissional profissionalAtualizado) {
        try {
            Profissional atualizado = profissionalService.atualizar(id, profissionalAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        profissionalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
