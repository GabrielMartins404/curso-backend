package com.task.task.dtos.tarefa;

import com.task.task.enums.StatusTarefa;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlterarStatusTarefaDTO {
    @NotNull(message = "O status da tarefa não pode ser nulo")
    private StatusTarefa status;
}
