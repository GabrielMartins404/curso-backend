package com.task.task.models;

import java.util.UUID;

import jakarta.persistence.Entity;
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
    private UUID id;

    private String descricao;

    public Categoria(String descricao) {
        this.id = UUID.randomUUID();
        this.descricao = descricao;
    }

}
