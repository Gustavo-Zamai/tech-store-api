package com.techstore.api.service;

import com.techstore.api.dto.request.CategoriaRequest;
import com.techstore.api.dto.response.CategoriaResponse;
import com.techstore.api.entity.Categoria;
import com.techstore.api.exception.BusinessException;
import com.techstore.api.exception.ResourceNotFoundException;
import com.techstore.api.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    @Transactional
    public CategoriaResponse criar(CategoriaRequest request) {
        if (repository.existsByDescricao(request.getDescricao())) {
            throw new BusinessException("Categoria já existe: " + request.getDescricao());
        }
        Categoria c = Categoria.builder()
            .descricao(request.getDescricao())
            .dataCadastro(request.getDataCadastro())
            .ativo(request.isAtivo())
            .build();
        return toResponse(repository.save(c));
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponse> listarTodos() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional
    public CategoriaResponse atualizar(Integer id, CategoriaRequest request) {
        Categoria c = buscarEntidade(id);
        c.setDescricao(request.getDescricao());
        c.setDataCadastro(request.getDataCadastro());
        c.setAtivo(request.isAtivo());
        return toResponse(repository.save(c));
    }

    @Transactional
    public void deletar(Integer id) {
        buscarEntidade(id);
        repository.deleteById(id);
    }

    public Categoria buscarEntidade(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + id));
    }

    private CategoriaResponse toResponse(Categoria c) {
        CategoriaResponse res = new CategoriaResponse();
        res.setId(c.getId());
        res.setDescricao(c.getDescricao());
        res.setDataCadastro(c.getDataCadastro());
        res.setAtivo(c.isAtivo());
        return res;
    }
}
