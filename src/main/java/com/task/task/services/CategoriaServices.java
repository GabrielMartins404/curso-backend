package com.task.task.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.task.task.models.Categoria;
import com.task.task.repositories.CategoriaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //Cria um construtor com todos os campos como final
public class CategoriaServices {
    // Injeção de dependência
    private final CategoriaRepository categoriaRepository;

    //@Transactional //Garanto que cso ocorra algum erro, tudo será revertido
    public Categoria salvarCategoria(Categoria categoria){
        Categoria categoriaParaSalvar = new Categoria(categoria.getDescricao());
        return categoriaRepository.save(categoriaParaSalvar);
    }

    public List<Categoria> listaCategorias(){
        return categoriaRepository.findAll();
    }

    public Categoria buscarCategoriaPorId(UUID id){
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi possivel localizar categoria por ID"));
    }

    @Transactional //Garanto que cso ocorra algum erro, tudo será revertido
    public Categoria alterarCategoria(Categoria categoria){
        if(categoria.getDescricao() == null){
            throw new RuntimeException("A descrição da categoria não pode ser nula");
        }

        if(categoria.getDescricao().length() <= 2){
            throw new RuntimeException("A descrição da categoria não pode ter menor que 2 caracteres");
        }

        return categoriaRepository.save(categoria);
    }

    @Transactional //Garanto que cso ocorra algum erro, tudo será revertido
    public void excluirCategoria(UUID id){
        categoriaRepository.deleteById(id);
    }
    
}
