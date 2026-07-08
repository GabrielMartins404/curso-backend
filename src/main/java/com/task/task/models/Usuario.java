package com.task.task.models;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data //Cria os Getter e Setter automaticamente
@NoArgsConstructor //Criar um construtor vazio
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "senha",nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario") //Indica que usuário pode possuir uma lista de tarefas
    private List<Tarefa> tarefas;

    public Usuario(String nome, String email, String senha) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

}