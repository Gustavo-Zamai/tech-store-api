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
}
