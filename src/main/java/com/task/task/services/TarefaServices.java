package com.task.task.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.task.models.Tarefa;
import com.task.task.repositories.TarefaRepository;

import jakarta.transaction.Transactional;

@Service
public class TarefaServices {
    @Autowired // Injeção de dependência
    private TarefaRepository tarefaRepository;

    @Transactional //Garanto que cso ocorra algum erro, tudo será revertido
    public Tarefa salvarTarefa(Tarefa tarefa){
        if(tarefa.getTitulo() == null){
            throw new RuntimeException("A nome da tarefa não pode ser nula");
        }

        if(tarefa.getTitulo().length() <= 2){
            throw new RuntimeException("A nome da tarefa não pode ter menor que 2 caracteres");
        }

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listaTarefas(){
        return tarefaRepository.findAll();
    }

    public Tarefa buscarTarefaPorId(UUID id){
        return tarefaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar tarefa por ID"));
    }

    public Tarefa alterarTarefa(Tarefa tarefa){
        if(tarefa.getTitulo() == null){
            throw new RuntimeException("A nome da tarefa não pode ser nula");
        }

        if(tarefa.getTitulo().length() <= 2){
            throw new RuntimeException("A nome da tarefa não pode ter menor que 2 caracteres");
        }

        return tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(UUID id){
        tarefaRepository.deleteById(id);
    }
    
}
