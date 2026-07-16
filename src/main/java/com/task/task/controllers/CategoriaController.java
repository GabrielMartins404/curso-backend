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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
@Tag(name = "Categorias", description = "Endpoints para gerenciamento de categorias de tarefas")
public class CategoriaController {
    @Autowired
    private CategoriaServices categoriaServices;

    @GetMapping("/")
    @Operation(summary = "Listar todas as categorias", description = "Retorna a lista completa de categorias cadastradas no sistema")
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias(){
        return ResponseEntity.ok(categoriaServices.listaCategorias());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Retorna os dados de uma categoria específica a partir do seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Categoria não encontrada com o ID informado")
    })
    public ResponseEntity<CategoriaResponseDTO> buscarCategoriaPorId(@PathVariable UUID id){
        return ResponseEntity.ok(categoriaServices.buscarCategoriaPorId(id));
    }

    @PostMapping("/")
    @Operation(summary = "Cadastrar nova categoria", description = "Cria uma nova categoria de tarefa no sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoria cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de validação inválidos")
    })
    public ResponseEntity<CategoriaResponseDTO> salvarCategoria(@Valid @RequestBody CategoriaRequestDTO dto){
        return ResponseEntity.ok(categoriaServices.salvarCategoria(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar categoria", description = "Atualiza a descrição de uma categoria existente a partir do seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Categoria não encontrada ou dados de validação inválidos")
    })
    public ResponseEntity<CategoriaResponseDTO> alterarCategoria(@PathVariable UUID id, @Valid @RequestBody CategoriaRequestDTO dto){
        return ResponseEntity.ok(categoriaServices.alterarCategoria(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir categoria", description = "Remove uma categoria do sistema a partir do seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso"),
        @ApiResponse(responseCode = "400", description = "Categoria não encontrada com o ID informado")
    })
    public ResponseEntity<Void> excluirCategoria(@PathVariable UUID id){
        categoriaServices.excluirCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
