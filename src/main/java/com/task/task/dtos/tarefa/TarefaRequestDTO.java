package com.task.task.dtos.tarefa;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "DTO para requisição de cadastro/atualização de tarefa")
public class TarefaRequestDTO {

    @NotBlank(message = "O titulo da tarefa não pode ser nulo")
    @Size(min = 5, max = 100, message = "O titulo da tarefa deve ter entre 5 e 100 caracteres")
    @Schema(description = "Título da tarefa (entre 5 e 100 caracteres)", example = "Estudar Spring Boot")
    private String titulo;

    @Size(max = 500, message = "A descricao da tarefa deve ter no máximo 500 caracteres")
    @Schema(description = "Descrição detalhada da tarefa (máximo 500 caracteres)", example = "Estudar os módulos de Spring Security e JPA")
    private String descricao;

    @NotNull(message = "O usuario da tarefa não pode ser nulo")
    @Schema(description = "Identificador do usuário responsável pela tarefa")
    private UUID usuarioId;

    @NotNull(message = "A categoria da tarefa não pode ser nula")
    @Schema(description = "Identificador da categoria da tarefa")
    private UUID categoriaId;
}
