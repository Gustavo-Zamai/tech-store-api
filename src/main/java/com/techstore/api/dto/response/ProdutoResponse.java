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

    // ---------- Dados fiscais / NFe e NFC-e ----------
    private String ncm;
    private String cfop;
    private String cest;
    private String unidadeComercial;
    private String unidadeTributavel;
    private String gtinEan;
    private Integer origemMercadoria;
    private String cstCsosnIcms;
    private BigDecimal aliquotaIcms;
    private String cstPis;
    private BigDecimal aliquotaPis;
    private String cstCofins;
    private BigDecimal aliquotaCofins;

    // ---------- IBS / CBS / Imposto Seletivo ----------
    private String cstIbsCbs;
    private String cClassTrib;
    private String cBenef;
    private BigDecimal aliquotaIbsEstadual;
    private BigDecimal aliquotaIbsMunicipal;
    private BigDecimal aliquotaCbs;
    private Boolean sujeitoImpostoSeletivo;
    private BigDecimal aliquotaImpostoSeletivo;
}
