package com.api.othon.controller;

import com.api.othon.model.Agendamento;
import com.api.othon.model.Filial;
import com.api.othon.model.Inventario;
import com.api.othon.model.VendaDTO;
import com.api.othon.model.VendaItem;
import com.api.othon.model.Vendas;
import com.api.othon.services.VendasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendas")
public class VendasController {

    private final VendasService vendasService;

    @Autowired
    public VendasController(VendasService vendasService) {
        this.vendasService = vendasService;
    }
  @GetMapping("/dia")
    public List<Vendas> listarVendasDoDia() {
        return vendasService.listarVendasDoDia();
    }
    @GetMapping
    public ResponseEntity<List<VendaDTO>> listarTodos() {
        List<VendaDTO> vendas = vendasService.listarTodos();
        return ResponseEntity.ok(vendas);
    }
    

    @PostMapping
    public ResponseEntity<String> criarVenda(@RequestBody Vendas vendas) {
        // Verifica se a filial e o profissional estão informados
        if (vendas.getFilial() == null || vendas.getProfissional() == null) {
            return ResponseEntity.badRequest().body("Filial ou profissional não informados."); // Retorna erro se filial ou profissional não estiverem informados
        }
    
        // Associar os itens da venda à venda antes de salvar
        for (VendaItem item : vendas.getVendaItems()) {
            item.setVenda(vendas); // Configurar a venda para cada item
        }
    
        // Salva a venda e seus itens
        Vendas novaVenda = vendasService.salvar(vendas);
        
        return ResponseEntity.ok("Venda criada com sucesso: " + novaVenda.getId()); // Retorna mensagem de sucesso com o ID da nova venda
    }
    
    
    
    
    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> buscarVendaPorId(@PathVariable Long id) {
        Optional<VendaDTO> venda = vendasService.buscarPorId(id);
        return venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendas> atualizarVenda(@PathVariable Long id, @RequestBody Vendas vendasAtualizado) {
        try {
            Vendas vendaAtualizada = vendasService.atualizar(id, vendasAtualizado);
            return ResponseEntity.ok(vendaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        vendasService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
