package com.techstore.api.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemVendaResponse {
    private Integer id;
    private Integer idProduto;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal total;
}
