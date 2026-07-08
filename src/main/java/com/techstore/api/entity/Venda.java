package com.techstore.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "venda")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    @Column(name = "data_venda")
    private LocalDateTime dataVenda;

    @Column(name = "metodo_pagamento", nullable = false, length = 80)
    private String metodoPagamento;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(precision = 10, scale = 2)
    private BigDecimal desconto;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(length = 500)
    private String observacao;

    @Column(length = 50)
    private String status;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemVenda> itens;

    // ---------- Emissão fiscal (NFe/NFC-e) ----------
    // Preenchidos depois da criação da venda, pelo (futuro) módulo de
    // emissão - não fazem parte do fluxo de criar()/atualizar() ainda,
    // porque a Venda hoje não tem endpoint de PUT (venda fechada não se edita).

    // Modelo do documento fiscal: 55-NFe, 65-NFC-e. Nulo enquanto não emitida.
    @Column(name = "documento_fiscal_tipo")
    private Integer documentoFiscalTipo;

    @Column(name = "chave_acesso", length = 44)
    private String chaveAcesso;

    @Column(name = "numero_nota_fiscal")
    private Integer numeroNotaFiscal;

    @Column(name = "serie_nota_fiscal")
    private Integer serieNotaFiscal;

    @Column(name = "protocolo_autorizacao", length = 30)
    private String protocoloAutorizacao;

    // PENDENTE, AUTORIZADA, REJEITADA, CANCELADA, NAO_EMITIDA
    @Column(name = "status_emissao_fiscal", length = 20)
    @Builder.Default
    private String statusEmissaoFiscal = "NAO_EMITIDA";

    @Column(name = "motivo_rejeicao", length = 500)
    private String motivoRejeicao;

    // Indicador de presença do comprador (indPres): 0-Não se aplica,
    // 1-Presencial, 2-Internet, 3-Teleatendimento, 4-Entrega a domicílio, 9-Outros
    @Column(name = "indicador_presenca")
    private Integer indicadorPresenca;

    @Column(name = "data_autorizacao")
    private LocalDateTime dataAutorizacao;

    @Column(name = "caminho_xml", length = 500)
    private String caminhoXml;

    @PrePersist
    public void prePersist() {
        if (dataVenda == null) dataVenda = LocalDateTime.now();
    }
}
