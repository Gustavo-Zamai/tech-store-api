package com.techstore.api.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProdutoResponse {
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal precoVenda;
    private BigDecimal precoCompra;
    private Integer quantidade;
    private Integer idFornecedor;
    private String nomeFornecedor;
    private Integer idCategoria;
    private String descricaoCategoria;
    private Integer quantidadeMinima;
    private LocalDateTime dataCadastro;
    private boolean ativo;
}
