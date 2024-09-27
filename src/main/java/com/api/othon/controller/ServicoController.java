package com.api.othon.controller;

import com.api.othon.model.Filial;
import com.api.othon.model.Profissional;
import com.api.othon.model.Servico;
import com.api.othon.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public List<Servico> listarTodos() {
        return servicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        Optional<Servico> servico = servicoService.buscarPorId(id);
        return servico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/Antigo")
    public Servico criar(@RequestBody Servico servico) {
        return servicoService.salvar1(servico);
    }
    @PostMapping(consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public Servico criars(
            @RequestParam("nome") String nome,
            @RequestParam("descricao") String descricao,
            @RequestParam("preco") BigDecimal preco,
            @RequestParam("duracao") Integer duracao,
            @RequestParam("filialId") Filial filialId,
            @RequestPart("imagem") MultipartFile imagem) throws IOException {
                System.out.println(nome);
                System.out.println(nome);
                System.out.println(nome);
                System.out.println(nome);
                System.out.println(nome);

        Servico servico = new Servico();
        servico.setNome(nome);
        servico.setDescricao(descricao);
        servico.setPreco(preco);
        servico.setDuracao(duracao);
        servico.setFilial(filialId);
    
        // Salvar o profissional junto com a imagem
        return servicoService.salvar(servico, imagem);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody Servico servicoAtualizado) {
        try {
            Servico atualizado = servicoService.atualizar(id, servicoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
