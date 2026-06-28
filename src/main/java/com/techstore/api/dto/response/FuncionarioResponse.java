package com.techstore.api.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FuncionarioResponse {
    private Integer id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String cargo;
    private String nivelAcesso;
    private String telefone;
    private String cep;
    private String endereco;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer idEmpresa;
    private String nomeEmpresa;
    private String imagemUrl;
    private LocalDateTime dataContratacao;
    private boolean ativo;
}
