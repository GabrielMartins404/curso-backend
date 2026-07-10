package com.task.task.models;

import java.util.UUID;

import com.task.task.enums.StatusTarefa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarefa")
@Data //Cria os Getter e Setter automaticamente
@NoArgsConstructor //Criar um construtor vazio
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "titulo",nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private StatusTarefa status;

    //Indica relação com usuário
    @ManyToOne //Indica que Varias tarefas se relacionam com um unico usuário
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    public Tarefa(String titulo, StatusTarefa status, Usuario usuario, Categoria categoria) {
        this.titulo = titulo;
        this.status = status;
        this.usuario = usuario;
        this.categoria = categoria;
    }
}