package com.techstore.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteRequest {

    @NotBlank(message = "Nome completo é obrigatório")
    @Size(max = 200)
    private String nomeCompleto;

    @NotBlank(message = "CPF é obrigatório")
    @Size(max = 20)
    private String cpf;

    @NotBlank(message = "E-mail é obrigatório")
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

    private LocalDateTime dataCadastro;

    @NotBlank(message = "Informe se o cliente está ativo")
    private boolean ativo;
}
