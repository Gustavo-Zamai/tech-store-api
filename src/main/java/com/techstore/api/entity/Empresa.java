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

    // ---------- Dados fiscais / NFe e NFC-e ----------

    @Column(name = "nome_fantasia", length = 150)
    private String nomeFantasia;

    @Column(name = "inscricao_estadual", length = 20)
    private String inscricaoEstadual;

    @Column(name = "inscricao_municipal", length = 20)
    private String inscricaoMunicipal;

    // Código de Regime Tributário (CRT): 1-Simples Nacional,
    // 2-Simples Nacional excesso de sublimite, 3-Regime Normal
    @Column(name = "regime_tributario")
    private Integer regimeTributario;

    @Column(name = "cnae_fiscal", length = 10)
    private String cnaeFiscal;

    // Código IBGE do município/UF - exigido no XML da NFe/NFC-e (tag cMun)
    @Column(name = "codigo_municipio_ibge", length = 10)
    private String codigoMunicipioIbge;

    @Column(name = "codigo_uf_ibge", length = 5)
    private String codigoUfIbge;

    // Ambiente de emissão (tpAmb): 1-Produção, 2-Homologação. Vale tanto
    // para NFe quanto para NFC-e.
    @Column(name = "ambiente_nfe")
    private Integer ambienteNfe;

    @Column(name = "serie_nfe")
    private Integer serieNfe;

    @Column(name = "proximo_numero_nfe")
    private Integer proximoNumeroNfe;

    @Column(name = "serie_nfce")
    private Integer serieNfce;

    @Column(name = "proximo_numero_nfce")
    private Integer proximoNumeroNfce;

    // Certificado digital A1 (.pfx/.p12) usado para assinar as notas.
    // ATENÇÃO: a senha fica em texto plano, igual ao login de funcionário -
    // considerar criptografar antes de ir para produção.
    @Column(name = "certificado_caminho", length = 500)
    private String certificadoCaminho;

    @Column(name = "certificado_senha", length = 200)
    private String certificadoSenha;

    @Column(name = "certificado_validade")
    private LocalDateTime certificadoValidade;

    // CSC (Código de Segurança do Contribuinte) - exigido só para NFC-e,
    // usado para gerar o QR Code.
    @Column(name = "csc_id", length = 10)
    private String cscId;

    @Column(name = "csc_token", length = 100)
    private String cscToken;

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
