package com.techstore.api.service;

import com.techstore.api.dto.request.FuncionarioRequest;
import com.techstore.api.dto.response.FuncionarioResponse;
import com.techstore.api.entity.Empresa;
import com.techstore.api.entity.Funcionario;
import com.techstore.api.exception.BusinessException;
import com.techstore.api.exception.ResourceNotFoundException;
import com.techstore.api.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final EmpresaService empresaService;

    @Transactional
    public FuncionarioResponse criar(FuncionarioRequest request) {
        if (repository.existsByCpf(request.getCpf())) {
            throw new BusinessException("CPF já cadastrado: " + request.getCpf());
        }
        if (repository.existsByEmail(request.getEmail())) {
            throw new BusinessException("E-mail já cadastrado: " + request.getEmail());
        }
        Funcionario f = toEntity(request);
        return toResponse(repository.save(f));
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponse> listarTodos() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FuncionarioResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponse> listarPorEmpresa(Integer idEmpresa) {
        return repository.findByEmpresaId(idEmpresa).stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional
    public FuncionarioResponse atualizar(Integer id, FuncionarioRequest request) {
        Funcionario f = buscarEntidade(id);
        if (!request.getCpf().equals(f.getCpf()) && repository.existsByCpf(request.getCpf())) {
            throw new BusinessException("CPF já cadastrado: " + request.getCpf());
        }
        if (!request.getEmail().equals(f.getEmail()) && repository.existsByEmail(request.getEmail())) {
            throw new BusinessException("E-mail já cadastrado: " + request.getEmail());
        }
        atualizarEntidade(f, request);
        return toResponse(repository.save(f));
    }

    @Transactional
    public void deletar(Integer id) {
        buscarEntidade(id);
        repository.deleteById(id);
    }

    public Funcionario buscarEntidade(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id: " + id));
    }

    private Funcionario toEntity(FuncionarioRequest r) {
        Empresa empresa = r.getIdEmpresa() != null ? empresaService.buscarEntidade(r.getIdEmpresa()) : null;
        return Funcionario.builder()
                .nomeCompleto(r.getNomeCompleto())
                .cpf(r.getCpf())
                .email(r.getEmail())
                .senha(r.getSenha())
                .cargo(r.getCargo())
                .nivelAcesso(r.getNivelAcesso())
                .telefone(r.getTelefone())
                .cep(r.getCep())
                .endereco(r.getEndereco())
                .numero(r.getNumero())
                .bairro(r.getBairro())
                .cidade(r.getCidade())
                .estado(r.getEstado())
                .empresa(empresa)
                .imagemUrl(r.getImagemUrl())
                .dataContratacao(r.getDataContratacao())
                .ativo(r.isAtivo())
                .build();
    }

    private void atualizarEntidade(Funcionario f, FuncionarioRequest r) {
        Empresa empresa = r.getIdEmpresa() != null ? empresaService.buscarEntidade(r.getIdEmpresa()) : null;
        f.setNomeCompleto(r.getNomeCompleto());
        f.setCpf(r.getCpf());
        f.setEmail(r.getEmail());
        f.setSenha(r.getSenha());
        f.setCargo(r.getCargo());
        f.setNivelAcesso(r.getNivelAcesso());
        f.setTelefone(r.getTelefone());
        f.setCep(r.getCep());
        f.setEndereco(r.getEndereco());
        f.setNumero(r.getNumero());
        f.setBairro(r.getBairro());
        f.setCidade(r.getCidade());
        f.setEstado(r.getEstado());
        f.setEmpresa(empresa);
        f.setImagemUrl(r.getImagemUrl());
        f.setAtivo(r.isAtivo());
    }

    private FuncionarioResponse toResponse(Funcionario f) {
        FuncionarioResponse res = new FuncionarioResponse();
        res.setId(f.getId());
        res.setNomeCompleto(f.getNomeCompleto());
        res.setCpf(f.getCpf());
        res.setEmail(f.getEmail());
        res.setCargo(f.getCargo());
        res.setNivelAcesso(f.getNivelAcesso());
        res.setTelefone(f.getTelefone());
        res.setCep(f.getCep());
        res.setEndereco(f.getEndereco());
        res.setNumero(f.getNumero());
        res.setBairro(f.getBairro());
        res.setCidade(f.getCidade());
        res.setEstado(f.getEstado());
        res.setImagemUrl(f.getImagemUrl());
        res.setDataContratacao(f.getDataContratacao());
        res.setAtivo(f.isAtivo());
        if (f.getEmpresa() != null) {
            res.setIdEmpresa(f.getEmpresa().getId());
            res.setNomeEmpresa(f.getEmpresa().getNome());
        }
        return res;
    }
}
