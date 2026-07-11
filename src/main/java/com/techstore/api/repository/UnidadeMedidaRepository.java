package com.techstore.api.repository;

import com.techstore.api.entity.UnidadeMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Integer> {
    boolean existsBySigla(String sigla);
}
