package com.techstore.api.service;

import com.techstore.api.dto.request.ItemVendaRequest;
import com.techstore.api.dto.request.VendaRequest;
import com.techstore.api.dto.response.ItemVendaResponse;
import com.techstore.api.dto.response.VendaResponse;
import com.techstore.api.entity.*;
import com.techstore.api.exception.BusinessException;
import com.techstore.api.exception.ResourceNotFoundException;
import com.techstore.api.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository repository;
    private final ClienteService clienteService;
    private final FuncionarioService funcionarioService;
    private final ProdutoService produtoService;

    @Transactional
    public VendaResponse criar(VendaRequest request) {
        Cliente cliente = clienteService.buscarEntidade(request.getIdCliente());
        Funcionario funcionario = funcionarioService.buscarEntidade(request.getIdFuncionario());

        List<ItemVenda> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (ItemVendaRequest itemReq : request.getItens()) {
            Produto produto = produtoService.buscarEntidade(itemReq.getIdProduto());
            if (produto.getQuantidade() < itemReq.getQuantidade()) {
                throw new BusinessException("Estoque insuficiente para o produto: " + produto.getNome()
                        + ". Disponível: " + produto.getQuantidade());
            }
            BigDecimal subtotal = produto.getPrecoVenda().multiply(BigDecimal.valueOf(itemReq.getQuantidade()));
            total = total.add(subtotal);

            // Baixa estoque
            produto.setQuantidade(produto.getQuantidade() - itemReq.getQuantidade());
            produtoService.buscarEntidade(produto.getId()); // garante que a entidade está gerenciada

            ItemVenda item = ItemVenda.builder()
                    .produto(produto)
                    .quantidade(itemReq.getQuantidade())
                    .total(subtotal)
                    .build();
            itens.add(item);
        }

        Venda venda = Venda.builder()
                .cliente(cliente)
                .funcionario(funcionario)
                .dataVenda(LocalDateTime.now())
                .metodoPagamento(request.getMetodoPagamento())
                .total(total)
                .build();

        venda = repository.save(venda);

        for (ItemVenda item : itens) {
            item.setVenda(venda);
        }
        venda.setItens(itens);
        venda = repository.save(venda);

        return toResponse(venda);
    }

    @Transactional(readOnly = true)
    public List<VendaResponse> listarTodos() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VendaResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<VendaResponse> listarPorCliente(Integer idCliente) {
        return repository.findByClienteId(idCliente).stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VendaResponse> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return repository.findByDataVendaBetween(inicio, fim).stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional
    public void deletar(Integer id) {
        buscarEntidade(id);
        repository.deleteById(id);
    }

    private Venda buscarEntidade(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada com id: " + id));
    }

    private VendaResponse toResponse(Venda v) {
        VendaResponse res = new VendaResponse();
        res.setId(v.getId());
        res.setIdCliente(v.getCliente().getId());
        res.setNomeCliente(v.getCliente().getNomeCompleto());
        res.setIdFuncionario(v.getFuncionario().getId());
        res.setNomeFuncionario(v.getFuncionario().getNomeCompleto());
        res.setDataVenda(v.getDataVenda());
        res.setMetodoPagamento(v.getMetodoPagamento());
        res.setTotal(v.getTotal());
        if (v.getItens() != null) {
            res.setItens(v.getItens().stream().map(this::toItemResponse).collect(Collectors.toList()));
        }
        return res;
    }

    private ItemVendaResponse toItemResponse(ItemVenda i) {
        ItemVendaResponse res = new ItemVendaResponse();
        res.setId(i.getId());
        res.setIdProduto(i.getProduto().getId());
        res.setNomeProduto(i.getProduto().getNome());
        res.setQuantidade(i.getQuantidade());
        res.setTotal(i.getTotal());
        return res;
    }
}
