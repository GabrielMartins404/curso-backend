package com.task.task.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.task.dtos.usuario.UsuarioRequestDTO;
import com.task.task.dtos.usuario.UsuarioResponseDTO;
import com.task.task.services.UsuarioServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping("/")
    @Operation(summary = "Listar todos os usuários", description = "Retorna a lista completa de usuários cadastrados no sistema")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios(){
        return ResponseEntity.ok(usuarioServices.listaUsuarios());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário específico a partir do seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Usuário não encontrado com o ID informado")
    })
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable UUID id){
        return ResponseEntity.ok(usuarioServices.buscarUsuarioPorId(id));
    }

    @PostMapping("/")
    @Operation(summary = "Cadastrar novo usuário", description = "Cria um novo usuário no sistema com nome, email e senha")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de validação inválidos")
    })
    public ResponseEntity<UsuarioResponseDTO> salvarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioDTO){
        return ResponseEntity.ok(usuarioServices.salvarUsuario(usuarioDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente a partir do seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Usuário não encontrado ou dados de validação inválidos")
    })
    public ResponseEntity<UsuarioResponseDTO> alterarUsuario(@PathVariable UUID id, @Valid @RequestBody UsuarioRequestDTO usuarioDTO){
        return ResponseEntity.ok(usuarioServices.alterarUsuario(id, usuarioDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuário", description = "Remove um usuário do sistema a partir do seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
        @ApiResponse(responseCode = "400", description = "Usuário não encontrado com o ID informado")
    })
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id){
        usuarioServices.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
