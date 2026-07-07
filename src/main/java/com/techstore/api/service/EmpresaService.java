package com.techstore.api.service;

import com.techstore.api.dto.request.EmpresaRequest;
import com.techstore.api.dto.response.EmpresaResponse;
import com.techstore.api.entity.Empresa;
import com.techstore.api.exception.BusinessException;
import com.techstore.api.exception.ResourceNotFoundException;
import com.techstore.api.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository repository;

    @Transactional
    public EmpresaResponse criar(EmpresaRequest request) {
        if (request.getCnpj() != null && repository.existsByCnpj(request.getCnpj())) {
            throw new BusinessException("CNPJ já cadastrado: " + request.getCnpj());
        }
        if (request.getEmail() != null && repository.existsByEmail(request.getEmail())) {
            throw new BusinessException("E-mail já cadastrado: " + request.getEmail());
        }
        Empresa empresa = toEntity(request);
        return toResponse(repository.save(empresa));
    }

    @Transactional(readOnly = true)
    public List<EmpresaResponse> listarTodos() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmpresaResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional
    public EmpresaResponse atualizar(Integer id, EmpresaRequest request) {
        Empresa empresa = buscarEntidade(id);
        if (request.getCnpj() != null && !request.getCnpj().equals(empresa.getCnpj())
                && repository.existsByCnpj(request.getCnpj())) {
            throw new BusinessException("CNPJ já cadastrado: " + request.getCnpj());
        }
        if (request.getEmail() != null && !request.getEmail().equals(empresa.getEmail())
                && repository.existsByEmail(request.getEmail())) {
            throw new BusinessException("E-mail já cadastrado: " + request.getEmail());
        }
        atualizarEntidade(empresa, request);
        return toResponse(repository.save(empresa));
    }

    @Transactional
    public void deletar(Integer id) {
        buscarEntidade(id);
        repository.deleteById(id);
    }

    public Empresa buscarEntidade(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com id: " + id));
    }

    private Empresa toEntity(EmpresaRequest r) {
        return Empresa.builder()
                .nome(r.getNome())
                .cnpj(r.getCnpj())
                .email(r.getEmail())
                .telefone(r.getTelefone())
                .cep(r.getCep())
                .endereco(r.getEndereco())
                .numero(r.getNumero())
                .bairro(r.getBairro())
                .cidade(r.getCidade())
                .estado(r.getEstado())
                .logoUrl(r.getLogoUrl())
                .ativo(r.getAtivo() != null ? r.getAtivo() : true)
                .nomeFantasia(r.getNomeFantasia())
                .inscricaoEstadual(r.getInscricaoEstadual())
                .inscricaoMunicipal(r.getInscricaoMunicipal())
                .regimeTributario(r.getRegimeTributario())
                .cnaeFiscal(r.getCnaeFiscal())
                .codigoMunicipioIbge(r.getCodigoMunicipioIbge())
                .codigoUfIbge(r.getCodigoUfIbge())
                .ambienteNfe(r.getAmbienteNfe())
                .serieNfe(r.getSerieNfe())
                .proximoNumeroNfe(r.getProximoNumeroNfe())
                .serieNfce(r.getSerieNfce())
                .proximoNumeroNfce(r.getProximoNumeroNfce())
                .certificadoCaminho(r.getCertificadoCaminho())
                .certificadoSenha(r.getCertificadoSenha())
                .certificadoValidade(r.getCertificadoValidade())
                .cscId(r.getCscId())
                .cscToken(r.getCscToken())
                .build();
    }

    private void atualizarEntidade(Empresa e, EmpresaRequest r) {
        e.setNome(r.getNome());
        e.setCnpj(r.getCnpj());
        e.setEmail(r.getEmail());
        e.setTelefone(r.getTelefone());
        e.setCep(r.getCep());
        e.setEndereco(r.getEndereco());
        e.setNumero(r.getNumero());
        e.setBairro(r.getBairro());
        e.setCidade(r.getCidade());
        e.setEstado(r.getEstado());
        e.setLogoUrl(r.getLogoUrl());
        if (r.getAtivo() != null) e.setAtivo(r.getAtivo());
        e.setNomeFantasia(r.getNomeFantasia());
        e.setInscricaoEstadual(r.getInscricaoEstadual());
        e.setInscricaoMunicipal(r.getInscricaoMunicipal());
        e.setRegimeTributario(r.getRegimeTributario());
        e.setCnaeFiscal(r.getCnaeFiscal());
        e.setCodigoMunicipioIbge(r.getCodigoMunicipioIbge());
        e.setCodigoUfIbge(r.getCodigoUfIbge());
        e.setAmbienteNfe(r.getAmbienteNfe());
        e.setSerieNfe(r.getSerieNfe());
        e.setProximoNumeroNfe(r.getProximoNumeroNfe());
        e.setSerieNfce(r.getSerieNfce());
        e.setProximoNumeroNfce(r.getProximoNumeroNfce());
        // Senha do certificado só é atualizada se vier preenchida - evita
        // apagar a senha já salva quando o formulário reenvia em branco.
        if (r.getCertificadoSenha() != null && !r.getCertificadoSenha().isBlank()) {
            e.setCertificadoSenha(r.getCertificadoSenha());
        }
        e.setCertificadoCaminho(r.getCertificadoCaminho());
        e.setCertificadoValidade(r.getCertificadoValidade());
        e.setCscId(r.getCscId());
        e.setCscToken(r.getCscToken());
    }

    private EmpresaResponse toResponse(Empresa e) {
        EmpresaResponse res = new EmpresaResponse();
        res.setId(e.getId());
        res.setNome(e.getNome());
        res.setCnpj(e.getCnpj());
        res.setEmail(e.getEmail());
        res.setTelefone(e.getTelefone());
        res.setCep(e.getCep());
        res.setEndereco(e.getEndereco());
        res.setNumero(e.getNumero());
        res.setBairro(e.getBairro());
        res.setCidade(e.getCidade());
        res.setEstado(e.getEstado());
        res.setLogoUrl(e.getLogoUrl());
        res.setDataCadastro(e.getDataCadastro());
        res.setAtivo(e.getAtivo());
        res.setNomeFantasia(e.getNomeFantasia());
        res.setInscricaoEstadual(e.getInscricaoEstadual());
        res.setInscricaoMunicipal(e.getInscricaoMunicipal());
        res.setRegimeTributario(e.getRegimeTributario());
        res.setCnaeFiscal(e.getCnaeFiscal());
        res.setCodigoMunicipioIbge(e.getCodigoMunicipioIbge());
        res.setCodigoUfIbge(e.getCodigoUfIbge());
        res.setAmbienteNfe(e.getAmbienteNfe());
        res.setSerieNfe(e.getSerieNfe());
        res.setProximoNumeroNfe(e.getProximoNumeroNfe());
        res.setSerieNfce(e.getSerieNfce());
        res.setProximoNumeroNfce(e.getProximoNumeroNfce());
        res.setCertificadoCaminho(e.getCertificadoCaminho());
        res.setCertificadoValidade(e.getCertificadoValidade());
        res.setCscId(e.getCscId());
        res.setCscToken(e.getCscToken());
        return res;
    }
}
