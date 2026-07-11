package com.techstore.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MarcaRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 150)
    private String nome;

    private LocalDateTime dataCadastro;

    @NotNull(message = "Informe se a marca está ativa")
    private boolean ativo;
}
