package com.techstore.api.repository;

import com.techstore.api.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByCpf(String cpf);
    Optional<Funcionario> findByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    List<Funcionario> findByEmpresaId(Integer idEmpresa);
    
    // Listar funcionários por empresa
    List<Funcionario> findByEmpresaId(Long empresaId);
}
