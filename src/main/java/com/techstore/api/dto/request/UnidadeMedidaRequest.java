package com.techstore.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UnidadeMedidaRequest {

    @NotBlank(message = "Sigla é obrigatória")
    @Size(max = 6)
    private String sigla;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 100)
    private String descricao;

    private LocalDateTime dataCadastro;

    @NotNull(message = "Informe se a unidade está ativa")
    private boolean ativo;
}
