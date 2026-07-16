package com.task.task.dtos.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO para requisição de cadastro/atualização de usuário")
public class UsuarioRequestDTO {
    @NotBlank(message = "O nome do usuario não pode ser nulo")
    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String name;

    @NotBlank(message = "O email do usuario não pode ser nulo")
    @Email(message = "O email não é válido")
    @Schema(description = "Email do usuário (usado como identificador de login)", example = "joao@email.com")
    private String email;

    @NotBlank(message = "A senha do usuario não pode ser nula")
    @Schema(description = "Senha do usuário", example = "senha123")
    private String senha;
}
