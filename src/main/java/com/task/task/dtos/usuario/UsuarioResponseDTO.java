package com.task.task.dtos.usuario;

import java.util.UUID;

import com.task.task.models.Usuario;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private UUID id;
    private String name;
    private String email;

    public UsuarioResponseDTO(Usuario usuario){
        this.id = usuario.getId();
        this.name = usuario.getNome();
        this.email = usuario.getEmail();
    }
}
