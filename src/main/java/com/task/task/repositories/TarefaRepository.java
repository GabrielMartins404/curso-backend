package com.task.task.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.task.models.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {
    
}
