package com.techstore.api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_completo", nullable = false, length = 200)
    private String nomeCompleto;

    @Column(unique = true, nullable = false, length = 20)
    private String cpf;

    @Column(unique = true, nullable = false, length = 200)
    private String email;

    @Column(nullable = false, length = 15)
    private String senha;

    @Column(nullable = false, length = 75)
    private String cargo;

    @Column
    private BigDecimal salario;

    @Column(name = "nivel_acesso", nullable = false, length = 50)
    private String nivelAcesso;

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

    @Column(name = "data_contratacao", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataContratacao;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @Column(name = "imagem_url", length = 500)
    private String imagemUrl;
}
