package com.techstore.api.repository;

import com.techstore.api.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findByClienteId(Integer idCliente);
    List<Venda> findByFuncionarioId(Integer idFuncionario);
    List<Venda> findByDataVendaBetween(LocalDateTime inicio, LocalDateTime fim);
}
