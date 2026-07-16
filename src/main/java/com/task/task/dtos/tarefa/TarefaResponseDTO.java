package com.task.task.dtos.tarefa;

import java.util.UUID;

import com.task.task.models.Tarefa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para resposta com os dados da tarefa")
public class TarefaResponseDTO {
    @Schema(description = "Identificador único da tarefa")
    private UUID id;

    @Schema(description = "Título da tarefa", example = "Estudar Spring Boot")
    private String titulo;

    @Schema(description = "Status atual da tarefa", example = "PENDENTE")
    private String status;

    @Schema(description = "Identificador do usuário responsável")
    private UUID usuarioId;

    @Schema(description = "Nome do usuário responsável", example = "João Silva")
    private String nomeUsuario;

    @Schema(description = "Identificador da categoria")
    private UUID categoriaId;

    @Schema(description = "Descrição da categoria", example = "Estudos")
    private String descricaoCategoria;

    public TarefaResponseDTO(Tarefa tarefa){
        this.id = tarefa.getId();
        this.titulo = tarefa.getTitulo();
        this.status = tarefa.getStatus().name();
        this.usuarioId = tarefa.getUsuario().getId();
        this.nomeUsuario = tarefa.getUsuario().getNome();
        this.categoriaId = tarefa.getCategoria().getId();
        this.descricaoCategoria = tarefa.getCategoria().getDescricao();
    }
}
