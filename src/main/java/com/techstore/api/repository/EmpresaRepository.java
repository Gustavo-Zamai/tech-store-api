package com.techstore.api.repository;

import com.techstore.api.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    Optional<Empresa> findByCnpj(String cnpj);
    Optional<Empresa> findByEmail(String email);
    boolean existsByCnpj(String cnpj);
    boolean existsByEmail(String email);
}
