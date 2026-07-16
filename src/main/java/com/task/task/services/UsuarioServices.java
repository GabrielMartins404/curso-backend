package com.task.task.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.task.task.dtos.usuario.UsuarioRequestDTO;
import com.task.task.dtos.usuario.UsuarioResponseDTO;
import com.task.task.models.Usuario;
import com.task.task.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServices {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO dto){
      Usuario usuario = new Usuario(dto.getName(), dto.getEmail(), passwordEncoder.encode(dto.getSenha()));
      usuarioRepository.save(usuario);
      return new UsuarioResponseDTO(usuario);
    }

    public List<UsuarioResponseDTO> listaUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(u -> new UsuarioResponseDTO(u)).toList();

    }

    public UsuarioResponseDTO buscarUsuarioPorId(UUID id){
        Usuario usuario =  usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar usuario por ID"));

        return new UsuarioResponseDTO(usuario);
    }

    public UsuarioResponseDTO alterarUsuario(UUID id, UsuarioRequestDTO dto){
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar usuario por ID"));
        
        usuario.setNome(dto.getName());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        UsuarioResponseDTO usuarioSalvoDTO = new UsuarioResponseDTO(usuarioSalvo);
        return usuarioSalvoDTO;
    }

    public void excluirUsuario(UUID id){
        Usuario usuario =  usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar usuario por ID"));
        usuarioRepository.delete(usuario);
    }
    
}
