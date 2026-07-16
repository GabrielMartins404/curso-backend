package com.task.task.dtos.categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO para requisição de cadastro/atualização de categoria")
public class CategoriaRequestDTO {
    @NotBlank(message = "A descricao da categoria não pode ser nula")
    @Schema(description = "Descrição da categoria", example = "Estudos")
    private String descricao;
}
