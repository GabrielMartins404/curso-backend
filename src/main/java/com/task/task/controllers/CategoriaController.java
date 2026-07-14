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

import com.task.task.dtos.categoria.CategoriaRequestDTO;
import com.task.task.dtos.categoria.CategoriaResponseDTO;
import com.task.task.services.CategoriaServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaServices categoriaServices;

    @GetMapping("/") //GET /categoria
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias(){
        return ResponseEntity.ok(categoriaServices.listaCategorias());
    }

    @GetMapping("/{id}") //GET /categoria/{id}
    public ResponseEntity<CategoriaResponseDTO> buscarCategoriaPorId(@PathVariable UUID id){
        return ResponseEntity.ok(categoriaServices.buscarCategoriaPorId(id));
    }

    @PostMapping("/") //POST /categoria
    public ResponseEntity<CategoriaResponseDTO> salvarCategoria(@Valid @RequestBody CategoriaRequestDTO dto){
        return ResponseEntity.ok(categoriaServices.salvarCategoria(dto));
    }

    @PutMapping("/{id}") //PUT /categoria/{id}
    public ResponseEntity<CategoriaResponseDTO> alterarCategoria(@PathVariable UUID id, @Valid @RequestBody CategoriaRequestDTO dto){
        return ResponseEntity.ok(categoriaServices.alterarCategoria(id, dto));
    }

    @DeleteMapping("/{id}") //DELETE /categoria/{id}
    public ResponseEntity<Void> excluirCategoria(@PathVariable UUID id){
        categoriaServices.excluirCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
