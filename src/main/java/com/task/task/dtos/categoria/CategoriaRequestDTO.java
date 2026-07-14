package com.task.task.dtos.categoria;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaRequestDTO {
    @NotBlank(message = "A descricao da categoria não pode ser nula")
    private String descricao;
}
