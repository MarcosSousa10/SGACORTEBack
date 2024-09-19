package com.api.othon.controller;

import com.api.othon.model.Transacao;
import com.api.othon.services.RelatorioVendasService;
import com.api.othon.services.TransacaoService;
import com.api.othon.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;
    @Autowired
    private RelatorioVendasService relatorioVendasService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public List<Transacao> listarTodas() {
        return transacaoService.listarTodas();
    }

	@GetMapping("/relatorio-vendas")
	public ResponseEntity<byte[]> relatorioVendas(
			@RequestParam(value = "inicio", required= false, defaultValue = "") String inicio
            
	){
		Date dataInicio = DateUtils.fromString(inicio);
	
		var relatorioGerado = relatorioVendasService.gerarRelatorio(dataInicio);
		var headers = new HttpHeaders();
		var fileName = "relatorio-vendas.pdf";
		headers.setContentDispositionFormData("inline; filename=\"" +fileName+ "\"", fileName);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		var responseEntity = new ResponseEntity<>(relatorioGerado, headers, HttpStatus.OK);
		return responseEntity;
	}

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> buscarPorId(@PathVariable Long id) {
        Optional<Transacao> transacao = transacaoService.buscarPorId(id);
        return transacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criars(@RequestBody Transacao transacao) {
        try {
            Transacao transacaoSalva = transacaoService.salvar(transacao);
            return ResponseEntity.ok("Transação efetuada com sucesso: ");
        } catch (Exception e) {
            // Log do erro se necessário
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a transação: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transacao> atualizar(@PathVariable Long id, @RequestBody Transacao transacaoAtualizada) {
        try {
            Transacao atualizado = transacaoService.atualizar(id, transacaoAtualizada);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        transacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
