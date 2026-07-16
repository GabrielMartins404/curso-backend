package com.task.task.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.task.task.dtos.categoria.CategoriaRequestDTO;
import com.task.task.dtos.categoria.CategoriaResponseDTO;
import com.task.task.models.Categoria;
import com.task.task.repositories.CategoriaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaServices {
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public CategoriaResponseDTO salvarCategoria(CategoriaRequestDTO dto){
        Categoria categoriaParaSalvar = new Categoria(dto.getDescricao());
        return new CategoriaResponseDTO(categoriaRepository.save(categoriaParaSalvar));
    }

    public Page<CategoriaResponseDTO> listaCategorias(Pageable pageable){
        return categoriaRepository.findAll(pageable).map(CategoriaResponseDTO::new);
    }

    public CategoriaResponseDTO buscarCategoriaPorId(UUID id){
        return new CategoriaResponseDTO(categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar categoria por ID")));
    }

    @Transactional
    public CategoriaResponseDTO alterarCategoria(UUID id, CategoriaRequestDTO dto){
        Categoria categoriaParaAlterar = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar categoria por ID"));
        
            categoriaParaAlterar.setDescricao(dto.getDescricao());

        return new CategoriaResponseDTO(categoriaRepository.save(categoriaParaAlterar));
    }

    @Transactional
    public void excluirCategoria(UUID id){
        categoriaRepository.deleteById(id);
    }
    
}
