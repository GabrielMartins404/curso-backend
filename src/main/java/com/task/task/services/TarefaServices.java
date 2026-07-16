package com.task.task.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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


    @Transactional //Garanto que cso ocorra algum erro, tudo será revertido
    public TarefaResponseDTO salvarTarefa(TarefaRequestDTO dto){
        //Busco o usuário no banco de dados
        //Só posso efetivamente vincular se o usuário realmente existir
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar usuario por ID"));

        //Busco a categoria no banco de dados
        //Só posso efetivamente vincular se a categoria realmente existir
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar categoria por ID"));

        //Criar a tarefa usando o construtor interno. Dentro do construtor, estabeleço as regras necessárias
        Tarefa tarefa = new Tarefa(dto.getTitulo(), dto.getDescricao(),  usuario, categoria);

        //Salva permanentemente no banco de dados
        tarefaRepository.save(tarefa);

        //Converto a tarefa para um response DTO
        TarefaResponseDTO responseDTO = new TarefaResponseDTO(tarefa);
        
        //Retorno esse response DTO
        return responseDTO;
    }

    @Transactional
    public List<TarefaResponseDTO> listaTarefas(){

        //Busco todas as tarefas do banco de dados
        //Converto cada tarefa para um response DTO
        //Retorno a lista de response DTO
        return tarefaRepository.findAll().stream()
            .map(t -> new TarefaResponseDTO(t))
            .collect(Collectors.toList());
    }

    @Transactional
    public TarefaResponseDTO buscarTarefaPorId(UUID id){
        //Busco a tarefa no banco de dados
        //Se a tarefa não existir, lanço uma exceção
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar tarefa por ID"));
        return new TarefaResponseDTO(tarefa);
    }

    @Transactional
    public TarefaResponseDTO alterarTarefa(UUID id, TarefaRequestDTO dto){
        //Alteração da tarefa seguira o roteiro
        //Busco a tarefa no banco de dados
        //Se a tarefa não existir, lanço uma exceção
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar tarefa por ID"));

        //Busco os objetos completos vindos do DTO
        //Só posso efetivamente vincular se o usuário realmente existir
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar usuario por ID"));
        //Só posso efetivamente vincular se a categoria realmente existir
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Nãoizar categoria por ID"));


        //Altero os campos da tarefa
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setUsuario(usuario);
        tarefa.setCategoria(categoria);

        //Salvo permanentemente no banco de dados
        tarefaRepository.save(tarefa);
        
        return new TarefaResponseDTO(tarefa);
    }

    public TarefaResponseDTO alterarStatusTarefa(UUID id, AlterarStatusTarefaDTO dto){
        //Busco a tarefa no banco de dados
        //Se a tarefa não existir, lanço uma exceção
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar tarefa por ID"));

        //Altero o status da tarefa
        tarefa.setStatus(dto.getStatus());

        //Salvo permanentemente no banco de dados
        tarefaRepository.save(tarefa);

        return new TarefaResponseDTO(tarefa);
    }

    @Transactional
    public void excluirTarefa(UUID id){
        //Excluo a tarefa do banco de dados
        tarefaRepository.deleteById(id);
    }
    
}
