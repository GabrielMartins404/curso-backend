package com.task.task.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.task.task.dtos.categoria.CategoriaRequestDTO;
import com.task.task.dtos.categoria.CategoriaResponseDTO;
import com.task.task.models.Categoria;
import com.task.task.repositories.CategoriaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //Cria um construtor com todos os campos como final
public class CategoriaServices {
    // Injeção de dependência
    private final CategoriaRepository categoriaRepository;

    @Transactional //Garanto que cso ocorra algum erro, tudo será revertido
    public CategoriaResponseDTO salvarCategoria(CategoriaRequestDTO dto){
        Categoria categoriaParaSalvar = new Categoria(dto.getDescricao());
        return new CategoriaResponseDTO(categoriaRepository.save(categoriaParaSalvar));
    }


    public List<CategoriaResponseDTO> listaCategorias(){
        return categoriaRepository.findAll().stream()
            .map(CategoriaResponseDTO::new)
            .toList();
    }

    public CategoriaResponseDTO buscarCategoriaPorId(UUID id){
        return new CategoriaResponseDTO(categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar categoria por ID")));
    }

    @Transactional //Garanto que cso ocorra algum erro, tudo será revertido
    public CategoriaResponseDTO alterarCategoria(UUID id, CategoriaRequestDTO dto){
        Categoria categoriaParaAlterar = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar categoria por ID"));
        
            categoriaParaAlterar.setDescricao(dto.getDescricao());

        return new CategoriaResponseDTO(categoriaRepository.save(categoriaParaAlterar));
    }

    @Transactional //Garanto que cso ocorra algum erro, tudo será revertido
    public void excluirCategoria(UUID id){
        categoriaRepository.deleteById(id);
    }
    
}
