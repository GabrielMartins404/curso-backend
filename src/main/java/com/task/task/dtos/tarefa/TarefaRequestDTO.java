package com.task.task.dtos.tarefa;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TarefaRequestDTO {

    @NotBlank(message = "O titulo da tarefa não pode ser nulo")
    @Size(min = 5, max = 100, message = "O titulo da tarefa deve ter entre 5 e 100 caracteres")
    private String titulo;

    @Size(max = 500, message = "A descricao da tarefa deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "O usuario da tarefa não pode ser nulo")
    private UUID usuarioId; //Nunca recebo o objeto diretamente, ao invés disso, recebo unicamente o ID do objeto

    @NotNull(message = "A categoria da tarefa não pode ser nula")
    private UUID categoriaId;
}
