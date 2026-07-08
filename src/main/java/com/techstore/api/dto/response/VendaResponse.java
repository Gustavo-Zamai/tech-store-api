package com.techstore.api.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VendaResponse {
    private Integer id;
    private Integer idCliente;
    private String nomeCliente;
    private Integer idFuncionario;
    private String nomeFuncionario;
    private LocalDateTime dataVenda;
    private String metodoPagamento;
    private BigDecimal total;
    private BigDecimal desconto;
    private BigDecimal subtotal;
    private String observacao;
    private String status;
    private List<ItemVendaResponse> itens;

    // ---------- Emissão fiscal (NFe/NFC-e) ----------
    private Integer documentoFiscalTipo;
    private String chaveAcesso;
    private Integer numeroNotaFiscal;
    private Integer serieNotaFiscal;
    private String protocoloAutorizacao;
    private String statusEmissaoFiscal;
    private String motivoRejeicao;
    private Integer indicadorPresenca;
    private LocalDateTime dataAutorizacao;
}
