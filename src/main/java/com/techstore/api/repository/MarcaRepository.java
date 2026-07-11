package com.techstore.api.repository;

import com.techstore.api.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    boolean existsByNome(String nome);
}
