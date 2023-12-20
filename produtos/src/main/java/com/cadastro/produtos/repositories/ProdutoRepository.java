package com.cadastro.produtos.repositories;

import com.cadastro.produtos.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    boolean existsByNome(String nome);
}
