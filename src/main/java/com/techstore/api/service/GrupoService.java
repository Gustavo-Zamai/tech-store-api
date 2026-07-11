package com.techstore.api.service;

import com.techstore.api.dto.request.GrupoRequest;
import com.techstore.api.dto.response.GrupoResponse;
import com.techstore.api.entity.Grupo;
import com.techstore.api.exception.BusinessException;
import com.techstore.api.exception.ResourceNotFoundException;
import com.techstore.api.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GrupoService {

    private final GrupoRepository repository;

    @Transactional
    public GrupoResponse criar(GrupoRequest request) {
        if (repository.existsByDescricao(request.getDescricao())) {
            throw new BusinessException("Grupo já existe: " + request.getDescricao());
        }
        Grupo c = Grupo.builder()
            .descricao(request.getDescricao())
            .ativo(request.isAtivo())
            .dataCadastro(request.getDataCadastro() != null ? request.getDataCadastro() : java.time.LocalDateTime.now())
            .build();
        return toResponse(repository.save(c));
    }

    @Transactional(readOnly = true)
    public List<GrupoResponse> listarTodos() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GrupoResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional
    public GrupoResponse atualizar(Integer id, GrupoRequest request) {
        Grupo c = buscarEntidade(id);
        c.setDescricao(request.getDescricao());
        c.setAtivo(request.isAtivo());
        return toResponse(repository.save(c));
    }

    @Transactional
    public void deletar(Integer id) {
        buscarEntidade(id);
        repository.deleteById(id);
    }

    public Grupo buscarEntidade(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupo não encontrado com id: " + id));
    }

    private GrupoResponse toResponse(Grupo c) {
        GrupoResponse res = new GrupoResponse();
        res.setId(c.getId());
        res.setDescricao(c.getDescricao());
        res.setDataCadastro(c.getDataCadastro());
        res.setAtivo(c.isAtivo());
        return res;
    }
}
