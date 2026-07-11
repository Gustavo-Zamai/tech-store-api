package com.techstore.api.service;

import com.techstore.api.dto.request.UnidadeMedidaRequest;
import com.techstore.api.dto.response.UnidadeMedidaResponse;
import com.techstore.api.entity.UnidadeMedida;
import com.techstore.api.exception.BusinessException;
import com.techstore.api.exception.ResourceNotFoundException;
import com.techstore.api.repository.UnidadeMedidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnidadeMedidaService {

    private final UnidadeMedidaRepository repository;

    @Transactional
    public UnidadeMedidaResponse criar(UnidadeMedidaRequest request) {
        if (repository.existsBySigla(request.getSigla())) {
            throw new BusinessException("Unidade de medida já existe: " + request.getSigla());
        }
        UnidadeMedida u = UnidadeMedida.builder()
            .sigla(request.getSigla())
            .descricao(request.getDescricao())
            .ativo(request.isAtivo())
            .dataCadastro(request.getDataCadastro() != null ? request.getDataCadastro() : java.time.LocalDateTime.now())
            .build();
        return toResponse(repository.save(u));
    }

    @Transactional(readOnly = true)
    public List<UnidadeMedidaResponse> listarTodos() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UnidadeMedidaResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional
    public UnidadeMedidaResponse atualizar(Integer id, UnidadeMedidaRequest request) {
        UnidadeMedida u = buscarEntidade(id);
        u.setSigla(request.getSigla());
        u.setDescricao(request.getDescricao());
        u.setAtivo(request.isAtivo());
        return toResponse(repository.save(u));
    }

    @Transactional
    public void deletar(Integer id) {
        buscarEntidade(id);
        repository.deleteById(id);
    }

    public UnidadeMedida buscarEntidade(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unidade de medida não encontrada com id: " + id));
    }

    private UnidadeMedidaResponse toResponse(UnidadeMedida u) {
        UnidadeMedidaResponse res = new UnidadeMedidaResponse();
        res.setId(u.getId());
        res.setSigla(u.getSigla());
        res.setDescricao(u.getDescricao());
        res.setDataCadastro(u.getDataCadastro());
        res.setAtivo(u.isAtivo());
        return res;
    }
}
