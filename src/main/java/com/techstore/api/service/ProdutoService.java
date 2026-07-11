package com.techstore.api.service;

import com.techstore.api.dto.request.ProdutoRequest;
import com.techstore.api.dto.response.ProdutoResponse;
import com.techstore.api.entity.Grupo;
import com.techstore.api.entity.Fornecedor;
import com.techstore.api.entity.Marca;
import com.techstore.api.entity.Produto;
import com.techstore.api.entity.UnidadeMedida;
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
    private final GrupoService grupoService;
    private final MarcaService marcaService;
    private final UnidadeMedidaService unidadeMedidaService;

    @Transactional
    public ProdutoResponse criar(ProdutoRequest request) {
        Fornecedor fornecedor = fornecedorService.buscarEntidade(request.getIdFornecedor());
        Grupo grupo = grupoService.buscarEntidade(request.getIdGrupo());
        Marca marca = request.getIdMarca() != null ? marcaService.buscarEntidade(request.getIdMarca()) : null;
        UnidadeMedida unidadeMedida = request.getIdUnidadeMedida() != null
                ? unidadeMedidaService.buscarEntidade(request.getIdUnidadeMedida()) : null;
        Produto p = Produto.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .precoVenda(request.getPrecoVenda())
                .precoCompra(request.getPrecoCompra())
                .quantidade(request.getQuantidade())
                .fornecedor(fornecedor)
                .grupo(grupo)
                .marca(marca)
                .unidadeMedida(unidadeMedida)
                .dataCadastro(request.getDataCadastro())
                .quantidadeMinima(request.getQuantidadeMinima())
                .ativo(request.isAtivo())
                .ncm(request.getNcm())
                .cfop(request.getCfop())
                .cest(request.getCest())
                .unidadeComercial(request.getUnidadeComercial())
                .unidadeTributavel(request.getUnidadeTributavel())
                .gtinEan(request.getGtinEan())
                .origemMercadoria(request.getOrigemMercadoria())
                .cstCsosnIcms(request.getCstCsosnIcms())
                .aliquotaIcms(request.getAliquotaIcms())
                .cstPis(request.getCstPis())
                .aliquotaPis(request.getAliquotaPis())
                .cstCofins(request.getCstCofins())
                .aliquotaCofins(request.getAliquotaCofins())
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
        Grupo grupo = grupoService.buscarEntidade(request.getIdGrupo());
        Marca marca = request.getIdMarca() != null ? marcaService.buscarEntidade(request.getIdMarca()) : null;
        UnidadeMedida unidadeMedida = request.getIdUnidadeMedida() != null
                ? unidadeMedidaService.buscarEntidade(request.getIdUnidadeMedida()) : null;
        p.setNome(request.getNome());
        p.setDescricao(request.getDescricao());
        p.setPrecoVenda(request.getPrecoVenda());
        p.setPrecoCompra(request.getPrecoCompra());
        p.setQuantidade(request.getQuantidade());
        p.setFornecedor(fornecedor);
        p.setGrupo(grupo);
        p.setMarca(marca);
        p.setUnidadeMedida(unidadeMedida);
        p.setDataCadastro(request.getDataCadastro());
        p.setQuantidadeMinima(request.getQuantidadeMinima());
        p.setAtivo(request.isAtivo());
        p.setNcm(request.getNcm());
        p.setCfop(request.getCfop());
        p.setCest(request.getCest());
        p.setUnidadeComercial(request.getUnidadeComercial());
        p.setUnidadeTributavel(request.getUnidadeTributavel());
        p.setGtinEan(request.getGtinEan());
        p.setOrigemMercadoria(request.getOrigemMercadoria());
        p.setCstCsosnIcms(request.getCstCsosnIcms());
        p.setAliquotaIcms(request.getAliquotaIcms());
        p.setCstPis(request.getCstPis());
        p.setAliquotaPis(request.getAliquotaPis());
        p.setCstCofins(request.getCstCofins());
        p.setAliquotaCofins(request.getAliquotaCofins());
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
        res.setIdGrupo(p.getGrupo().getId());
        res.setDescricaoGrupo(p.getGrupo().getDescricao());
        if (p.getMarca() != null) {
            res.setIdMarca(p.getMarca().getId());
            res.setNomeMarca(p.getMarca().getNome());
        }
        if (p.getUnidadeMedida() != null) {
            res.setIdUnidadeMedida(p.getUnidadeMedida().getId());
            res.setSiglaUnidadeMedida(p.getUnidadeMedida().getSigla());
        }
        res.setQuantidadeMinima(p.getQuantidadeMinima());
        res.setDataCadastro(p.getDataCadastro());
        res.setAtivo(p.isAtivo());
        res.setNcm(p.getNcm());
        res.setCfop(p.getCfop());
        res.setCest(p.getCest());
        res.setUnidadeComercial(p.getUnidadeComercial());
        res.setUnidadeTributavel(p.getUnidadeTributavel());
        res.setGtinEan(p.getGtinEan());
        res.setOrigemMercadoria(p.getOrigemMercadoria());
        res.setCstCsosnIcms(p.getCstCsosnIcms());
        res.setAliquotaIcms(p.getAliquotaIcms());
        res.setCstPis(p.getCstPis());
        res.setAliquotaPis(p.getAliquotaPis());
        res.setCstCofins(p.getCstCofins());
        res.setAliquotaCofins(p.getAliquotaCofins());
        return res;
    }
}
