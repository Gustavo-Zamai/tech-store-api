package com.techstore.api.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ClienteResponse {
    private Integer id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String telefone;
    private String cep;
    private String endereco;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private LocalDateTime dataCadastro;
    private boolean ativo;
}
