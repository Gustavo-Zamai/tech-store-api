package com.techstore.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fornecedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_completo", nullable = false, length = 250)
    private String nomeCompleto;

    @Column(unique = true, nullable = false, length = 20)
    private String cnpj;

    @Column(unique = true, nullable = false, length = 200)
    private String email;

    @Column(length = 30)
    private String telefone;

    @Column(length = 25)
    private String cep;

    @Column(length = 270)
    private String endereco;

    private Integer numero;

    @Column(length = 100)
    private String bairro;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2)
    private String estado;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    private boolean ativo;
}
