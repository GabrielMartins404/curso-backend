package com.task.task.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    @NotBlank(message = "O nome do usuario não pode ser nulo") //Validação que valida se o nome não está vazio ou nulo
    private String name;

    @NotBlank(message = "O email do usuario não pode ser nulo")
    @Email(message = "O email não é válido") //Validação do tipo de email
    private String email;

    @NotBlank(message = "A senha do usuario não pode ser nula")
    private String senha;
}
