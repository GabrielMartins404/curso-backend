package com.task.task.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Data //Cria os Getter e Setter automaticamente
@NoArgsConstructor //Criar um construtor vazio
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "descricao",nullable = false)
    private String descricao;

    public Categoria(String descricao) {
        this.id = UUID.randomUUID();
        this.descricao = descricao;
    }

}
