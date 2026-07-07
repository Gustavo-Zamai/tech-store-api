package com.techstore.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmpresaRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 150)
    private String nome;

    @Size(max = 20)
    private String cnpj;

    @Email(message = "E-mail inválido")
    @Size(max = 200)
    private String email;

    @Size(max = 30)
    private String telefone;

    @Size(max = 25)
    private String cep;

    @Size(max = 270)
    private String endereco;

    private Integer numero;

    @Size(max = 100)
    private String bairro;

    @Size(max = 100)
    private String cidade;

    @Size(max = 2)
    private String estado;

    @Size(max = 500)
    private String logoUrl;

    private Boolean ativo;

    // ---------- Dados fiscais / NFe e NFC-e ----------

    @Size(max = 150)
    private String nomeFantasia;

    @Size(max = 20)
    private String inscricaoEstadual;

    @Size(max = 20)
    private String inscricaoMunicipal;

    private Integer regimeTributario;

    @Size(max = 10)
    private String cnaeFiscal;

    @Size(max = 10)
    private String codigoMunicipioIbge;

    @Size(max = 5)
    private String codigoUfIbge;

    private Integer ambienteNfe;

    private Integer serieNfe;

    private Integer proximoNumeroNfe;

    private Integer serieNfce;

    private Integer proximoNumeroNfce;

    @Size(max = 500)
    private String certificadoCaminho;

    @Size(max = 200)
    private String certificadoSenha;

    private java.time.LocalDateTime certificadoValidade;

    @Size(max = 10)
    private String cscId;

    @Size(max = 100)
    private String cscToken;
}
