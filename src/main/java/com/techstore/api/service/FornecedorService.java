package com.techstore.api.service;

import com.techstore.api.dto.request.FornecedorRequest;
import com.techstore.api.dto.response.FornecedorResponse;
import com.techstore.api.entity.Fornecedor;
import com.techstore.api.exception.BusinessException;
import com.techstore.api.exception.ResourceNotFoundException;
import com.techstore.api.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository repository;

    @Transactional
    public FornecedorResponse criar(FornecedorRequest request) {
        if (repository.existsByCnpj(request.getCnpj())) {
            throw new BusinessException("CNPJ já cadastrado: " + request.getCnpj());
        }
        if (repository.existsByEmail(request.getEmail())) {
            throw new BusinessException("E-mail já cadastrado: " + request.getEmail());
        }
        return toResponse(repository.save(toEntity(request)));
    }

    @Transactional(readOnly = true)
    public List<FornecedorResponse> listarTodos() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FornecedorResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional
    public FornecedorResponse atualizar(Integer id, FornecedorRequest request) {
        Fornecedor f = buscarEntidade(id);
        if (!request.getCnpj().equals(f.getCnpj()) && repository.existsByCnpj(request.getCnpj())) {
            throw new BusinessException("CNPJ já cadastrado: " + request.getCnpj());
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

    public Fornecedor buscarEntidade(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com id: " + id));
    }

    private Fornecedor toEntity(FornecedorRequest r) {
        return Fornecedor.builder()
                .nomeCompleto(r.getNomeCompleto())
                .cnpj(r.getCnpj())
                .email(r.getEmail())
                .telefone(r.getTelefone())
                .cep(r.getCep())
                .endereco(r.getEndereco())
                .numero(r.getNumero())
                .bairro(r.getBairro())
                .cidade(r.getCidade())
                .estado(r.getEstado())
                .dataCadastro(r.getDataCadastro())
                .ativo(r.isAtivo())
                .build();
    }

    private void atualizarEntidade(Fornecedor f, FornecedorRequest r) {
        f.setNomeCompleto(r.getNomeCompleto());
        f.setCnpj(r.getCnpj());
        f.setEmail(r.getEmail());
        f.setTelefone(r.getTelefone());
        f.setCep(r.getCep());
        f.setEndereco(r.getEndereco());
        f.setNumero(r.getNumero());
        f.setBairro(r.getBairro());
        f.setCidade(r.getCidade());
        f.setEstado(r.getEstado());
        f.setAtivo(r.isAtivo());
    }

    private FornecedorResponse toResponse(Fornecedor f) {
        FornecedorResponse res = new FornecedorResponse();
        res.setId(f.getId());
        res.setNomeCompleto(f.getNomeCompleto());
        res.setCnpj(f.getCnpj());
        res.setEmail(f.getEmail());
        res.setTelefone(f.getTelefone());
        res.setCep(f.getCep());
        res.setEndereco(f.getEndereco());
        res.setNumero(f.getNumero());
        res.setBairro(f.getBairro());
        res.setCidade(f.getCidade());
        res.setEstado(f.getEstado());
        res.setDataCadastro(f.getDataCadastro());
        res.setAtivo(f.isAtivo());
        return res;
    }
}
