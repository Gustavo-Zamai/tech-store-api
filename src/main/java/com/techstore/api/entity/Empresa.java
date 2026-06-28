package com.techstore.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "empresa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(unique = true, length = 20)
    private String cnpj;

    @Column(unique = true, length = 200)
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

    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    @Builder.Default
    private Boolean ativo = true;

    @PrePersist
    public void prePersist() {
        if (dataCadastro == null) dataCadastro = LocalDateTime.now();
    }
}
