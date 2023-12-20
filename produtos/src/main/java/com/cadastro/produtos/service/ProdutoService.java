package com.cadastro.produtos.service;

import com.cadastro.produtos.models.Produto;
import com.cadastro.produtos.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Transactional
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public boolean existsByNome(String nome) {
        return produtoRepository.existsByNome(nome);
    }

    public Optional<Produto> findById(UUID id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }
}
