package com.task.task.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Transactional
    public Page<UsuarioResponseDTO> listaUsuarios(Pageable pageable){
        return usuarioRepository.findAll(pageable).map(UsuarioResponseDTO::new);
    }

    @Transactional
    public UsuarioResponseDTO buscarUsuarioPorId(UUID id){
        Usuario usuario =  usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar usuario por ID"));

        return new UsuarioResponseDTO(usuario);
    }

    @Transactional
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

    @Transactional
    public void excluirUsuario(UUID id){
        Usuario usuario =  usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar usuario por ID"));
        usuarioRepository.delete(usuario);
    }
    

    @Transactional
    public Usuario buscarUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        // Usa o username (que é o email) para buscar a entidade JPA completa do banco
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + username));
    }
}
