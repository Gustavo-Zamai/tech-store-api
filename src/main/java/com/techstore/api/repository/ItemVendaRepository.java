package com.techstore.api.repository;

import com.techstore.api.entity.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Integer> {
    List<ItemVenda> findByVendaId(Integer idVenda);
}
