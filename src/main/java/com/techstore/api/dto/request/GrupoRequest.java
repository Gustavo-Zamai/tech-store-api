package com.techstore.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class GrupoRequest {

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 220)
    private String descricao;

    private LocalDateTime dataCadastro;

    @NotNull(message = "Informe se o grupo está ativo")
    private boolean ativo;
}
