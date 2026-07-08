package com.techstore.api.service;

import com.techstore.api.dto.request.ClienteRequest;
import com.techstore.api.dto.response.ClienteResponse;
import com.techstore.api.entity.Cliente;
import com.techstore.api.exception.BusinessException;
import com.techstore.api.exception.ResourceNotFoundException;
import com.techstore.api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public ClienteResponse criar(ClienteRequest request) {
        if (repository.existsByCpf(request.getCpf())) {
            throw new BusinessException("CPF já cadastrado: " + request.getCpf());
        }
        if (repository.existsByEmail(request.getEmail())) {
            throw new BusinessException("E-mail já cadastrado: " + request.getEmail());
        }
        return toResponse(repository.save(toEntity(request)));
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> listarTodos() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional
    public ClienteResponse atualizar(Integer id, ClienteRequest request) {
        Cliente c = buscarEntidade(id);
        if (!request.getCpf().equals(c.getCpf()) && repository.existsByCpf(request.getCpf())) {
            throw new BusinessException("CPF já cadastrado: " + request.getCpf());
        }
        if (!request.getEmail().equals(c.getEmail()) && repository.existsByEmail(request.getEmail())) {
            throw new BusinessException("E-mail já cadastrado: " + request.getEmail());
        }
        atualizarEntidade(c, request);
        return toResponse(repository.save(c));
    }

    @Transactional
    public void deletar(Integer id) {
        buscarEntidade(id);
        repository.deleteById(id);
    }

    public Cliente buscarEntidade(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));
    }

    private Cliente toEntity(ClienteRequest r) {
        return Cliente.builder()
                .nomeCompleto(r.getNomeCompleto())
                .cpf(r.getCpf())
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
                .indicadorIe(r.getIndicadorIe())
                .build();
    }

    private void atualizarEntidade(Cliente c, ClienteRequest r) {
        c.setNomeCompleto(r.getNomeCompleto());
        c.setCpf(r.getCpf());
        c.setEmail(r.getEmail());
        c.setTelefone(r.getTelefone());
        c.setCep(r.getCep());
        c.setEndereco(r.getEndereco());
        c.setNumero(r.getNumero());
        c.setBairro(r.getBairro());
        c.setCidade(r.getCidade());
        c.setEstado(r.getEstado());
        c.setDataCadastro(r.getDataCadastro());
        c.setAtivo(r.isAtivo());
        c.setIndicadorIe(r.getIndicadorIe());
    }

    private ClienteResponse toResponse(Cliente c) {
        ClienteResponse res = new ClienteResponse();
        res.setId(c.getId());
        res.setNomeCompleto(c.getNomeCompleto());
        res.setCpf(c.getCpf());
        res.setEmail(c.getEmail());
        res.setTelefone(c.getTelefone());
        res.setCep(c.getCep());
        res.setEndereco(c.getEndereco());
        res.setNumero(c.getNumero());
        res.setBairro(c.getBairro());
        res.setCidade(c.getCidade());
        res.setEstado(c.getEstado());
        res.setDataCadastro(c.getDataCadastro());
        res.setAtivo(c.isAtivo());
        res.setIndicadorIe(c.getIndicadorIe());
        return res;
    }
}
