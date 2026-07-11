package com.techstore.api.repository;

import com.techstore.api.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByGrupoId(Integer idGrupo);
    List<Produto> findByFornecedorId(Integer idFornecedor);
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findByQuantidadeLessThan(Integer quantidade);
}
