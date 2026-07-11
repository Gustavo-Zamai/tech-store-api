package com.techstore.api.service;

import com.techstore.api.dto.request.MarcaRequest;
import com.techstore.api.dto.response.MarcaResponse;
import com.techstore.api.entity.Marca;
import com.techstore.api.exception.BusinessException;
import com.techstore.api.exception.ResourceNotFoundException;
import com.techstore.api.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository repository;

    @Transactional
    public MarcaResponse criar(MarcaRequest request) {
        if (repository.existsByNome(request.getNome())) {
            throw new BusinessException("Marca já existe: " + request.getNome());
        }
        Marca m = Marca.builder()
            .nome(request.getNome())
            .ativo(request.isAtivo())
            .dataCadastro(request.getDataCadastro() != null ? request.getDataCadastro() : java.time.LocalDateTime.now())
            .build();
        return toResponse(repository.save(m));
    }

    @Transactional(readOnly = true)
    public List<MarcaResponse> listarTodos() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MarcaResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional
    public MarcaResponse atualizar(Integer id, MarcaRequest request) {
        Marca m = buscarEntidade(id);
        m.setNome(request.getNome());
        m.setAtivo(request.isAtivo());
        return toResponse(repository.save(m));
    }

    @Transactional
    public void deletar(Integer id) {
        buscarEntidade(id);
        repository.deleteById(id);
    }

    public Marca buscarEntidade(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada com id: " + id));
    }

    private MarcaResponse toResponse(Marca m) {
        MarcaResponse res = new MarcaResponse();
        res.setId(m.getId());
        res.setNome(m.getNome());
        res.setDataCadastro(m.getDataCadastro());
        res.setAtivo(m.isAtivo());
        return res;
    }
}
