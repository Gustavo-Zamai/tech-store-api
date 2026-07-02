package com.techstore.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    //private String token;
    private Integer id;          // Mudando para Long (igual ao ID do Funcionario)
    private String nome;
    private String email;
    private String cargo;
    private Integer idEmpresa;   // Mudando para Long
}