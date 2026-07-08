package com.task.task.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.task.models.Categoria;
import com.task.task.services.CategoriaServices;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaServices categoriaServices;

    @GetMapping //GET /categoria
    public ResponseEntity<List<Categoria>> listarCategorias(){
        return ResponseEntity.ok(categoriaServices.listaCategorias());
    }

    @GetMapping("/{id}") //GET /categoria/{id}
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable UUID id){
        return ResponseEntity.ok(categoriaServices.buscarCategoriaPorId(id));
    }

    @PostMapping //POST /categoria
    public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.ok(categoriaServices.salvarCategoria(categoria));
    }

    @PutMapping("/{id}") //PUT /categoria/{id}
    public ResponseEntity<Categoria> alterarCategoria(@PathVariable UUID id, @RequestBody Categoria categoria){
        categoria.setId(id);
        return ResponseEntity.ok(categoriaServices.alterarCategoria(categoria));
    }

    @DeleteMapping("/{id}") //DELETE /categoria/{id}
    public ResponseEntity<Void> excluirCategoria(@PathVariable UUID id){
        categoriaServices.excluirCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
