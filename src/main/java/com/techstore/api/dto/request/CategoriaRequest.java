package com.techstore.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoriaRequest {

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 220)
    private String descricao;

    private LocalDateTime dataCadastro;

    @NotNull(message = "Informe se a categoria está ativa")
    private boolean ativo;
}
