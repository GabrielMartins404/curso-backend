package com.task.task.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO para requisição de login")
public class LoginRequestDTO {
    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "O email não é válido")
    @Schema(description = "Email do usuário", example = "usuario@email.com")
    private String email;

    @NotBlank(message = "A senha não pode ser nula")
    @Schema(description = "Senha do usuário", example = "senha123")
    private String senha;
}
