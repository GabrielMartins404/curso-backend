package com.task.task.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.task.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> { //Extendo qual a classe Model e qual o tipo do ID
    
}
