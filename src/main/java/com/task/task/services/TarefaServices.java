package com.task.task.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.task.task.dtos.tarefa.AlterarStatusTarefaDTO;
import com.task.task.dtos.tarefa.TarefaRequestDTO;
import com.task.task.dtos.tarefa.TarefaResponseDTO;
import com.task.task.models.Categoria;
import com.task.task.models.Tarefa;
import com.task.task.models.Usuario;
import com.task.task.repositories.CategoriaRepository;
import com.task.task.repositories.TarefaRepository;
import com.task.task.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaServices {
    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public TarefaResponseDTO salvarTarefa(TarefaRequestDTO dto){
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar usuario por ID"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar categoria por ID"));

        Tarefa tarefa = new Tarefa(dto.getTitulo(), dto.getDescricao(),  usuario, categoria);

        tarefaRepository.save(tarefa);

        return new TarefaResponseDTO(tarefa);
    }

    @Transactional
    public Page<TarefaResponseDTO> listaTarefas(Pageable pageable){
        return tarefaRepository.findAll(pageable).map(TarefaResponseDTO::new);
    }

    @Transactional
    public TarefaResponseDTO buscarTarefaPorId(UUID id){
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar tarefa por ID"));
        return new TarefaResponseDTO(tarefa);
    }

    @Transactional
    public TarefaResponseDTO alterarTarefa(UUID id, TarefaRequestDTO dto){
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar tarefa por ID"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar usuario por ID"));
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar categoria por ID"));

        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setUsuario(usuario);
        tarefa.setCategoria(categoria);

        tarefaRepository.save(tarefa);
        
        return new TarefaResponseDTO(tarefa);
    }

    public TarefaResponseDTO alterarStatusTarefa(UUID id, AlterarStatusTarefaDTO dto){
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar tarefa por ID"));

        tarefa.setStatus(dto.getStatus());

        tarefaRepository.save(tarefa);

        return new TarefaResponseDTO(tarefa);
    }

    @Transactional
    public void excluirTarefa(UUID id){
        tarefaRepository.deleteById(id);
    }
    
}
