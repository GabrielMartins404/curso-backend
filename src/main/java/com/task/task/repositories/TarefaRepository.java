package com.task.task.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.task.models.Tarefa;
import com.task.task.models.Usuario;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {
    Page<Tarefa> findByUsuario(Usuario usuario, Pageable pageable);
}
