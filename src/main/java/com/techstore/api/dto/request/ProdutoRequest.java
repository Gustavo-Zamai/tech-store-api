package com.techstore.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProdutoRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100)
    private String nome;

    @Size(max = 100)
    private String descricao;

    @NotNull(message = "Preço de Venda é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    private BigDecimal precoVenda;

    @NotNull(message = "Preço de Compra é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    private BigDecimal precoCompra;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 0, message = "Quantidade não pode ser negativa")
    private Integer quantidade;

    @NotNull(message = "Fornecedor é obrigatório")
    private Integer idFornecedor;

    @NotNull(message = "Categoria é obrigatória")
    private Integer idCategoria;

    @Min(value = 0, message = "Quantidade não pode ser negativa")
    private Integer quantidadeMinima;

    private LocalDateTime dataCadastro;

    @NotBlank
    private boolean ativo;
}
