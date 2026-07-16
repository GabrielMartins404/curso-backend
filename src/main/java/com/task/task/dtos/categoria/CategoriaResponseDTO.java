package com.task.task.dtos.categoria;

import java.util.UUID;

import com.task.task.models.Categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para resposta com os dados da categoria")
public class CategoriaResponseDTO {
    @Schema(description = "Identificador único da categoria")
    private UUID id;

    @Schema(description = "Descrição da categoria", example = "Estudos")
    private String descricao;

    public CategoriaResponseDTO(Categoria categoria){
        this.id = categoria.getId();
        this.descricao = categoria.getDescricao();
    }
}
