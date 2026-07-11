package com.techstore.api.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MarcaResponse {
    private Integer id;
    private String nome;
    private LocalDateTime dataCadastro;
    private boolean ativo;
}
