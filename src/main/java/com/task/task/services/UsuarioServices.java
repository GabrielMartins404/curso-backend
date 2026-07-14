package com.task.task.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.task.dtos.usuario.UsuarioRequestDTO;
import com.task.task.dtos.usuario.UsuarioResponseDTO;
import com.task.task.models.Usuario;
import com.task.task.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServices {
    @Autowired // Injeção de dependência
    private UsuarioRepository usuarioRepository;

    @Transactional //Garanto que cso ocorra algum erro, tudo será revertido
    public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO dto){
      //Preciso instanciar usuario usando o construtor
      Usuario usuario = new Usuario(dto.getName(), dto.getEmail(), dto.getSenha());
      //Salvo permanentemente no banco de dados
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
        //Busca o usuario pelo ID
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar usuario por ID"));
        
        //Altera os dados do usuario
        usuario.setNome(dto.getName());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        //Salva no banco de dados
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        //Conver para um dto de response
        UsuarioResponseDTO usuarioSalvoDTO = new UsuarioResponseDTO(usuarioSalvo);
        return usuarioSalvoDTO;
    }

    public void excluirUsuario(UUID id){
        //Busca o usuario pelo ID
        Usuario usuario =  usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar usuario por ID"));
        //Exclui o usuario do banco de dados
        usuarioRepository.delete(usuario);
    }
    
}
