package com.task.task.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "DTO para resposta de login")
public class LoginResponseDTO {
    @Schema(description = "Token JWT gerado após autenticação", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;
}
