package com.task.task.dtos.tarefa;

import java.util.UUID;

import com.task.task.models.Tarefa;

import lombok.Data;

@Data
public class TarefaResponseDTO {
    private UUID id;
    private String titulo;
    private String status;
    private UUID usuarioId;
    private String nomeUsuario;
    private UUID categoriaId;
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
