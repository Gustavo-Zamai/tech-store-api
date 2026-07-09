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

    @NotNull
    private boolean ativo;

    // ---------- Dados fiscais / NFe e NFC-e ----------
    // Nenhum desses é @NotNull de propósito: produto pode ser cadastrado
    // antes de todo o cadastro fiscal estar pronto, e passa a exigi-los só
    // na hora de efetivamente emitir a nota (validação que fica pro módulo
    // de emissão, não pro cadastro de produto).

    @Size(max = 8)
    private String ncm;

    @Size(max = 4)
    private String cfop;

    @Size(max = 7)
    private String cest;

    @Size(max = 6)
    private String unidadeComercial;

    @Size(max = 6)
    private String unidadeTributavel;

    @Size(max = 14)
    private String gtinEan;

    private Integer origemMercadoria;

    @Size(max = 4)
    private String cstCsosnIcms;

    private BigDecimal aliquotaIcms;

    @Size(max = 4)
    private String cstPis;

    private BigDecimal aliquotaPis;

    @Size(max = 4)
    private String cstCofins;

    private BigDecimal aliquotaCofins;

    // ---------- IBS / CBS / Imposto Seletivo ----------
    @Size(max = 3)
    private String cstIbsCbs;

    @Size(max = 6)
    private String cClassTrib;

    @Size(max = 10)
    private String cBenef;

    private BigDecimal aliquotaIbsEstadual;

    private BigDecimal aliquotaIbsMunicipal;

    private BigDecimal aliquotaCbs;

    private Boolean sujeitoImpostoSeletivo;

    private BigDecimal aliquotaImpostoSeletivo;
}
