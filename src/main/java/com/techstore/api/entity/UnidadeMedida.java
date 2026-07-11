package com.techstore.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidade_medida")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadeMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Sigla usada na NFe/NFC-e (unidadeComercial/unidadeTributavel do Produto): UN, KG, CX, L, M, etc.
    @Column(nullable = false, length = 6)
    private String sigla;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(name = "data_cadastro", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    private boolean ativo;
}
