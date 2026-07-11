package com.techstore.api.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UnidadeMedidaResponse {
    private Integer id;
    private String sigla;
    private String descricao;
    private LocalDateTime dataCadastro;
    private boolean ativo;
}
