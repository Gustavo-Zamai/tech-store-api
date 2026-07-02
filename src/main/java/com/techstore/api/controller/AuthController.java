package com.techstore.api.controller;

import com.techstore.api.dto.request.LoginRequest;
import com.techstore.api.dto.response.LoginResponse;
import com.techstore.api.entity.Funcionario;
import com.techstore.api.repository.FuncionarioRepository;
import com.techstore.api.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // Busca funcionário por email
        Funcionario funcionario = funcionarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("Email ou senha inválidos"));

        // Verifica a senha (em produção, use BCrypt ou similar)
        if (!funcionario.getSenha().equals(request.getSenha())) {
            throw new BusinessException("Email ou senha inválidos");
        }

        // Gera token simples (em produção, use JWT)
        String token = generateToken(funcionario);

        // Cria a resposta com todos os campos
        LoginResponse response = new LoginResponse(
                funcionario.getId(),
                funcionario.getNomeCompleto(),
                funcionario.getEmail(),
                funcionario.getCargo(),
                funcionario.getEmpresa() != null ? funcionario.getEmpresa().getId() : null
        );

        return ResponseEntity.ok(response);
    }

    private String generateToken(Funcionario funcionario) {
        // Simples geração de token - substituir por JWT em produção
        return "token-" + System.currentTimeMillis() + "-" + funcionario.getId();
    }
}