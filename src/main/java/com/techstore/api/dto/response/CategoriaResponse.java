package com.techstore.api.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CategoriaResponse {
    private Integer id;
    private String descricao;
    private LocalDateTime dataCadastro;
    private boolean ativo;
}
