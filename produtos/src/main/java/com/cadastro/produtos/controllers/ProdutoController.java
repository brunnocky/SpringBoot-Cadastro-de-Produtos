package com.cadastro.produtos.controllers;

import com.cadastro.produtos.dtos.ProdutoDto;
import com.cadastro.produtos.models.Produto;
import com.cadastro.produtos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id){

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> saveProduto(@RequestBody @Valid ProdutoDto produtoDto){
        if (produtoService.existsByNome(produtoDto.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto já cadastrado!");
        }
        var produto = new Produto();
        BeanUtils.copyProperties(produtoDto,produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putProdut(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProdutoDto produtoDto){
        Optional<Produto> produtoOptional = produtoService.findById(id);
        if (!produtoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto não encontrado!");
        }
        var produto = new Produto();
        BeanUtils.copyProperties(produtoDto, produto);
        produto.setId(produtoOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
        Optional<Produto> produtoOptional = produtoService.findById(id);
        if (!produtoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto não encontrado!");
        }

        produtoService.delete(produtoOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }


}
