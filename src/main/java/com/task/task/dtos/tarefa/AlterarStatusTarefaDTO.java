package com.task.task.dtos.tarefa;

import com.task.task.enums.StatusTarefa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "DTO para alteração do status de uma tarefa")
public class AlterarStatusTarefaDTO {
    @NotNull(message = "O status da tarefa não pode ser nulo")
    @Schema(description = "Novo status da tarefa", example = "EM_ANDAMENTO", allowableValues = {"PENDENTE", "EM_ANDAMENTO", "CONCLUIDA"})
    private StatusTarefa status;
}
