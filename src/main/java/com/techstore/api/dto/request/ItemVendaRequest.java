package com.techstore.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ItemVendaRequest {

    @NotNull(message = "Produto é obrigatório")
    private Integer idProduto;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade mínima é 1")
    private Integer quantidade;
}
