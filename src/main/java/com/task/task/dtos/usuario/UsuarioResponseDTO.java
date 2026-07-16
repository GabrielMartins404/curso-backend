package com.task.task.dtos.usuario;

import java.util.UUID;

import com.task.task.models.Usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para resposta com os dados do usuário")
public class UsuarioResponseDTO {
    @Schema(description = "Identificador único do usuário")
    private UUID id;

    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String name;

    @Schema(description = "Email do usuário", example = "joao@email.com")
    private String email;

    public UsuarioResponseDTO(Usuario usuario){
        this.id = usuario.getId();
        this.name = usuario.getNome();
        this.email = usuario.getEmail();
    }
}
