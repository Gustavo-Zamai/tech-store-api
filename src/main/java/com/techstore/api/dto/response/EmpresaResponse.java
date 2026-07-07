package com.techstore.api.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EmpresaResponse {
    private Integer id;
    private String nome;
    private String cnpj;
    private String email;
    private String telefone;
    private String cep;
    private String endereco;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String logoUrl;
    private LocalDateTime dataCadastro;
    private Boolean ativo;

    // ---------- Dados fiscais / NFe e NFC-e ----------
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private Integer regimeTributario;
    private String cnaeFiscal;
    private String codigoMunicipioIbge;
    private String codigoUfIbge;
    private Integer ambienteNfe;
    private Integer serieNfe;
    private Integer proximoNumeroNfe;
    private Integer serieNfce;
    private Integer proximoNumeroNfce;
    private String certificadoCaminho;
    // Propositalmente NÃO inclui certificadoSenha - dado sensível não deveria
    // voltar em resposta de leitura, só é usado internamente pela API.
    private LocalDateTime certificadoValidade;
    private String cscId;
    private String cscToken;
}
