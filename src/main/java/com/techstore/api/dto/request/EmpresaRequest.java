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
}
