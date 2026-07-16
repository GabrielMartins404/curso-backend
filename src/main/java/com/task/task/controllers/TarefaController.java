package com.task.task.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.task.dtos.tarefa.AlterarStatusTarefaDTO;
import com.task.task.dtos.tarefa.TarefaRequestDTO;
import com.task.task.dtos.tarefa.TarefaResponseDTO;
import com.task.task.services.TarefaServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefa")
@Tag(name = "Tarefas", description = "Endpoints para gerenciamento de tarefas")
public class TarefaController {
    @Autowired
    private TarefaServices tarefaServices;

    @GetMapping
    @Operation(summary = "Listar todas as tarefas", description = "Retorna a lista completa de tarefas cadastradas no sistema")
    public ResponseEntity<List<TarefaResponseDTO>> listarTarefas(){
        return ResponseEntity.ok(tarefaServices.listaTarefas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tarefa por ID", description = "Retorna os dados de uma tarefa específica a partir do seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Tarefa não encontrada com o ID informado")
    })
    public ResponseEntity<TarefaResponseDTO> buscarTarefaPorId(@PathVariable UUID id){
        return ResponseEntity.ok(tarefaServices.buscarTarefaPorId(id));
    }

    @PostMapping("/")
    @Operation(summary = "Cadastrar nova tarefa", description = "Cria uma nova tarefa vinculada a um usuário e uma categoria. O status inicial é PENDENTE")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarefa cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de validação inválidos ou usuário/categoria não encontrados")
    })
    public ResponseEntity<TarefaResponseDTO> salvarTarefa(@Valid @RequestBody TarefaRequestDTO tarefaDTO){
        return ResponseEntity.ok(tarefaServices.salvarTarefa(tarefaDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tarefa", description = "Atualiza os dados de uma tarefa existente (título, descrição, usuário e categoria)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Tarefa não encontrada ou dados de validação inválidos")
    })
    public ResponseEntity<TarefaResponseDTO> alterarTarefa(@PathVariable UUID id, @Valid @RequestBody TarefaRequestDTO tarefaDTO){
        return ResponseEntity.ok(tarefaServices.alterarTarefa(id, tarefaDTO));
    }

    @PutMapping("/status/{id}")
    @Operation(summary = "Alterar status da tarefa", description = "Altera o status de uma tarefa. Valores aceitos: PENDENTE, EM_ANDAMENTO, CONCLUIDA")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Status da tarefa alterado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Tarefa não encontrada ou status inválido")
    })
    public ResponseEntity<TarefaResponseDTO> alterarStatusTarefa(@PathVariable UUID id, @Valid @RequestBody AlterarStatusTarefaDTO alterarStatusDTO){
        return ResponseEntity.ok(tarefaServices.alterarStatusTarefa(id, alterarStatusDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir tarefa", description = "Remove uma tarefa do sistema a partir do seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso"),
        @ApiResponse(responseCode = "400", description = "Tarefa não encontrada com o ID informado")
    })
    public ResponseEntity<Void> excluirTarefa(@PathVariable UUID id){
        tarefaServices.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
