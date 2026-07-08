package com.techstore.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_completo", nullable = false, length = 200)
    private String nomeCompleto;

    @Column(unique = true, nullable = false, length = 20)
    private String cpf;

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

    @Column(name = "data_cadastro", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    private boolean ativo;

    // Indicador de IE do destinatário (indIEDest na NFe/NFC-e):
    // 1-Contribuinte ICMS, 2-Contribuinte isento de Inscrição, 9-Não Contribuinte
    @Column(name = "indicador_ie")
    private Integer indicadorIe;
}
