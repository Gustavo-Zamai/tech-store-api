package com.techstore.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 100)
    private String descricao;

    @Column(name = "preco_venda",nullable = false, precision = 10, scale = 2)
    private BigDecimal precoVenda;

    @Column(name = "preco_compra",nullable = false, precision = 10, scale = 2)
    private BigDecimal precoCompra;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "quantidade_minima")
    private Integer quantidadeMinima;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    private boolean ativo;

    // ---------- Dados fiscais / NFe e NFC-e ----------

    @Column(length = 8)
    private String ncm;

    @Column(length = 4)
    private String cfop;

    @Column(length = 7)
    private String cest;

    @Column(name = "unidade_comercial", length = 6)
    private String unidadeComercial;

    @Column(name = "unidade_tributavel", length = 6)
    private String unidadeTributavel;

    // Código de barras (GTIN/EAN). Usar "SEM GTIN" quando o produto não tiver.
    @Column(name = "gtin_ean", length = 14)
    private String gtinEan;

    // Origem da mercadoria (tabela do SPED): 0-Nacional, 1 a 8-Estrangeira/outras
    @Column(name = "origem_mercadoria")
    private Integer origemMercadoria;

    // CST (Regime Normal) ou CSOSN (Simples Nacional) do ICMS
    @Column(name = "cst_csosn_icms", length = 4)
    private String cstCsosnIcms;

    @Column(name = "aliquota_icms", precision = 5, scale = 2)
    private BigDecimal aliquotaIcms;

    @Column(name = "cst_pis", length = 4)
    private String cstPis;

    @Column(name = "aliquota_pis", precision = 5, scale = 2)
    private BigDecimal aliquotaPis;

    @Column(name = "cst_cofins", length = 4)
    private String cstCofins;

    @Column(name = "aliquota_cofins", precision = 5, scale = 2)
    private BigDecimal aliquotaCofins;

    // ---------- IBS / CBS / Imposto Seletivo (Reforma Tributária - EC 132/2023, LC 214/2025) ----------
    // Obrigatório declarar desde jan/2026 (alíquota-teste, sem custo extra real);
    // validações plenas em produção a partir de 03/08/2026 para Regime Normal (CRT=3).

    // CST específico do IBS/CBS (Grupo UB) - é diferente do CST de ICMS.
    @Column(name = "cst_ibs_cbs", length = 3)
    private String cstIbsCbs;

    // Detalhamento do CST, vinculado a um artigo específico da LC 214/2025
    // (ex: para CST 410, existem 27 cClassTrib diferentes).
    @Column(name = "c_class_trib", length = 6)
    private String cClassTrib;

    // Código de Benefício Fiscal (já existia para ICMS, passa a valer também para IBS/CBS)
    @Column(name = "c_benef", length = 10)
    private String cBenef;

    @Column(name = "aliquota_ibs_estadual", precision = 6, scale = 3)
    private BigDecimal aliquotaIbsEstadual;

    @Column(name = "aliquota_ibs_municipal", precision = 6, scale = 3)
    private BigDecimal aliquotaIbsMunicipal;

    @Column(name = "aliquota_cbs", precision = 6, scale = 3)
    private BigDecimal aliquotaCbs;

    // Produto sujeito ao Imposto Seletivo (cigarro, bebida alcoólica, veículo
    // poluente, etc - "imposto do pecado"). A maioria dos produtos NÃO é.
    @Column(name = "sujeito_imposto_seletivo")
    private Boolean sujeitoImpostoSeletivo;

    @Column(name = "aliquota_imposto_seletivo", precision = 6, scale = 3)
    private BigDecimal aliquotaImpostoSeletivo;
}
