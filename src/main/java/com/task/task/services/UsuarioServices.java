package com.task.task.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.task.dtos.usuario.UsuarioResponseDTO;
import com.task.task.models.Usuario;
import com.task.task.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServices {
    @Autowired // Injeção de dependência
    private UsuarioRepository usuarioRepository;

    @Transactional //Garanto que cso ocorra algum erro, tudo será revertido
    public Usuario salvarUsuario(Usuario usuario){
        if(usuario.getEmail() == null){
            throw new RuntimeException("A email não pode ser nula");
        }

        //CRIAR REGRA DE NEGOCIO QUE NÃO PERMITE DUPLICIDADE DE USUARIOS

        return usuarioRepository.save(usuario);
    }

    public List<UsuarioResponseDTO> listaUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(u -> new UsuarioResponseDTO(u)).toList();

    }

    public Usuario buscarUsuarioPorId(UUID id){
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar usuario por ID"));
    }

    public Usuario alterarUsuario(Usuario usuario){
        if(usuario.getEmail() == null){
            throw new RuntimeException("A email não pode ser nula");
        }

        if(usuario.getEmail().length() <= 2){
            throw new RuntimeException("A email não pode ter menor que 2 caracteres");
        }

        return usuarioRepository.save(usuario);
    }

    public void excluirUsuario(UUID id){
        usuarioRepository.deleteById(id);
    }
    
}
