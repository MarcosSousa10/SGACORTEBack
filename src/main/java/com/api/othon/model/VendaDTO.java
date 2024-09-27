package com.api.othon.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.api.othon.controller.dto.VendaItemDTO;
import com.api.othon.model.Vendas.MetodoPagamento;

public class VendaDTO {
    private Long vendaId;
    private LocalDateTime dataVenda;
    private BigDecimal vendaValorTotal;
    private Long vendaItemId;
    private Integer vendaItemQuantidade;
    private MetodoPagamento metodoPagamento;
    private Cliente cliente;
    private Profissional profissional;
    private Filial filial;
    private List<VendaItemDTO> itens; // Adicione este campo

    // Construtor
    public VendaDTO(Long vendaId, Date dataVenda, BigDecimal vendaValorTotal, Long vendaItemId,
                    Integer vendaItemQuantidade, MetodoPagamento metodoPagamento, Cliente cliente,
                    Profissional profissional, Filial filial) {
        this.vendaId = vendaId;
        this.dataVenda = convertToLocalDateTime(dataVenda);
        this.vendaValorTotal = vendaValorTotal;
        this.vendaItemId = vendaItemId;
        this.vendaItemQuantidade = vendaItemQuantidade;
        this.metodoPagamento = metodoPagamento;
        this.cliente = cliente; 
        this.profissional = profissional;
        this.filial = filial;
        this.itens = new ArrayList<>(); // Inicialize a lista de itens
    }

    // Método para obter itens
    public List<VendaItemDTO> getItens() {
        return itens;
    }

    // Método para adicionar um item
    public void addItem(VendaItemDTO item) {
        this.itens.add(item);
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        if (date == null) {
            return null; // Tratar o caso de data nula, se necessário
        }
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    // Getters e Setters
    public Long getVendaId() {
        return vendaId;
    }

    public void setVendaId(Long vendaId) {
        this.vendaId = vendaId;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getVendaValorTotal() {
        return vendaValorTotal;
    }

    public void setVendaValorTotal(BigDecimal vendaValorTotal) {
        this.vendaValorTotal = vendaValorTotal;
    }

    public Long getVendaItemId() {
        return vendaItemId; // Getter adicionado aqui
    }

    public void setVendaItemId(Long vendaItemId) {
        this.vendaItemId = vendaItemId;
    }

    public Integer getVendaItemQuantidade() {
        return vendaItemQuantidade;
    }

    public void setVendaItemQuantidade(Integer vendaItemQuantidade) {
        this.vendaItemQuantidade = vendaItemQuantidade;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
}
