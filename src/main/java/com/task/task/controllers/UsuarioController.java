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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping("/") //GET /usuario
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios(){
        return ResponseEntity.ok(usuarioServices.listaUsuarios());
    }

    @GetMapping("/{id}") //GET /usuario/{id}
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable UUID id){
        return ResponseEntity.ok(usuarioServices.buscarUsuarioPorId(id));
    }

    @PostMapping("/") //POST /usuario
    public ResponseEntity<UsuarioResponseDTO> salvarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioDTO){
        return ResponseEntity.ok(usuarioServices.salvarUsuario(usuarioDTO));
    }

    @PutMapping("/{id}") //PUT /usuario/{id}
    public ResponseEntity<UsuarioResponseDTO> alterarUsuario(@PathVariable UUID id, @Valid @RequestBody UsuarioRequestDTO usuarioDTO){
        return ResponseEntity.ok(usuarioServices.alterarUsuario(id, usuarioDTO));
    }

    @DeleteMapping("/{id}") //DELETE /usuario/{id}
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id){
        usuarioServices.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
