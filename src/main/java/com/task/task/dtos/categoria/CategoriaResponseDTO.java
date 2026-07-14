package com.task.task.dtos.categoria;

import java.util.UUID;

import com.task.task.models.Categoria;

import lombok.Data;

@Data
public class CategoriaResponseDTO {
    private UUID id;
    private String descricao;

    public CategoriaResponseDTO(Categoria categoria){
        this.id = categoria.getId();
        this.descricao = categoria.getDescricao();
    }
}
