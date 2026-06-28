package com.techstore.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FuncionarioRequest {

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

    @NotBlank(message = "Senha é obrigatória")
    @Size(max = 15)
    private String senha;

    @NotBlank(message = "Cargo é obrigatório")
    @Size(max = 75)
    private String cargo;

    @NotBlank(message = "Nível de acesso é obrigatório")
    @Size(max = 50)
    private String nivelAcesso;

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

    private Integer idEmpresa;

    @Size(max = 500)
    private String imagemUrl;

    private LocalDateTime dataContratacao;

    @NotNull
    private boolean ativo;
}
