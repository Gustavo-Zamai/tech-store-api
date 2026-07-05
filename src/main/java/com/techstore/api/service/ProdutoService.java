package com.techstore.api.service;

import com.techstore.api.dto.request.ProdutoRequest;
import com.techstore.api.dto.response.ProdutoResponse;
import com.techstore.api.entity.Categoria;
import com.techstore.api.entity.Fornecedor;
import com.techstore.api.entity.Produto;
import com.techstore.api.exception.ResourceNotFoundException;
import com.techstore.api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final FornecedorService fornecedorService;
    private final CategoriaService categoriaService;

    @Transactional
    public ProdutoResponse criar(ProdutoRequest request) {
        Fornecedor fornecedor = fornecedorService.buscarEntidade(request.getIdFornecedor());
        Categoria categoria = categoriaService.buscarEntidade(request.getIdCategoria());
        Produto p = Produto.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .precoVenda(request.getPrecoVenda())
                .precoCompra(request.getPrecoCompra())
                .quantidade(request.getQuantidade())
                .fornecedor(fornecedor)
                .categoria(categoria)
                .dataCadastro(request.getDataCadastro())
                .quantidadeMinima(request.getQuantidadeMinima())
                .ativo(request.isAtivo())
                .build();
        return toResponse(repository.save(p));
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> listarTodos() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProdutoResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome).stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> buscarEstoqueBaixo(Integer quantidade) {
        return repository.findByQuantidadeLessThan(quantidade).stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional
    public ProdutoResponse atualizar(Integer id, ProdutoRequest request) {
        Produto p = buscarEntidade(id);
        Fornecedor fornecedor = fornecedorService.buscarEntidade(request.getIdFornecedor());
        Categoria categoria = categoriaService.buscarEntidade(request.getIdCategoria());
        p.setNome(request.getNome());
        p.setDescricao(request.getDescricao());
        p.setPrecoVenda(request.getPrecoVenda());
        p.setPrecoCompra(request.getPrecoCompra());
        p.setQuantidade(request.getQuantidade());
        p.setFornecedor(fornecedor);
        p.setCategoria(categoria);
        p.setDataCadastro(request.getDataCadastro());
        p.setQuantidadeMinima(request.getQuantidadeMinima());
        p.setAtivo(request.isAtivo());
        return toResponse(repository.save(p));
    }

    @Transactional
    public void deletar(Integer id) {
        buscarEntidade(id);
        repository.deleteById(id);
    }

    public Produto buscarEntidade(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
    }

    private ProdutoResponse toResponse(Produto p) {
        ProdutoResponse res = new ProdutoResponse();
        res.setId(p.getId());
        res.setNome(p.getNome());
        res.setDescricao(p.getDescricao());
        res.setPrecoVenda(p.getPrecoVenda());
        res.setPrecoCompra(p.getPrecoCompra());
        res.setQuantidade(p.getQuantidade());
        res.setIdFornecedor(p.getFornecedor().getId());
        res.setNomeFornecedor(p.getFornecedor().getNomeCompleto());
        res.setIdCategoria(p.getCategoria().getId());
        res.setDescricaoCategoria(p.getCategoria().getDescricao());
        res.setQuantidadeMinima(p.getQuantidadeMinima());
        res.setDataCadastro(p.getDataCadastro());
        res.setAtivo(p.isAtivo());
        return res;
    }
}
