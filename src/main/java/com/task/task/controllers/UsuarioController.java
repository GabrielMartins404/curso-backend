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

import com.task.task.dtos.usuario.UsuarioResponseDTO;
import com.task.task.models.Usuario;
import com.task.task.services.UsuarioServices;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping //GET /usuario
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios(){
        return ResponseEntity.ok(usuarioServices.listaUsuarios());
    }

    @GetMapping("/{id}") //GET /usuario/{id}
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable UUID id){
        return ResponseEntity.ok(usuarioServices.buscarUsuarioPorId(id));
    }

    @PostMapping //POST /usuario
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioServices.salvarUsuario(usuario));
    }

    @PutMapping("/{id}") //PUT /usuario/{id}
    public ResponseEntity<Usuario> alterarUsuario(@PathVariable UUID id, @RequestBody Usuario usuario){
        usuario.setId(id);
        return ResponseEntity.ok(usuarioServices.alterarUsuario(usuario));
    }

    @DeleteMapping("/{id}") //DELETE /usuario/{id}
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id){
        usuarioServices.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
