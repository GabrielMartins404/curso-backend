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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    @Autowired
    private TarefaServices tarefaServices;

    @GetMapping //GET
    public ResponseEntity<List<TarefaResponseDTO>> listarTarefas(){
        return ResponseEntity.ok(tarefaServices.listaTarefas());
    }

    @GetMapping("/{id}") //GET /tarefa/{id}
    public ResponseEntity<TarefaResponseDTO> buscarTarefaPorId(@PathVariable UUID id){
        return ResponseEntity.ok(tarefaServices.buscarTarefaPorId(id));
    }

    @PostMapping("/") //POST /tarefa
    public ResponseEntity<TarefaResponseDTO> salvarTarefa(@Valid @RequestBody TarefaRequestDTO tarefaDTO){
        return ResponseEntity.ok(tarefaServices.salvarTarefa(tarefaDTO));
    }

    @PutMapping("/{id}") //PUT /tarefa/{id}
    public ResponseEntity<TarefaResponseDTO> alterarTarefa(@PathVariable UUID id, @Valid @RequestBody TarefaRequestDTO tarefaDTO){
        return ResponseEntity.ok(tarefaServices.alterarTarefa(id, tarefaDTO));
    }

    @PutMapping("/status/{id}") //PUT /tarefa/status/{id}
    public ResponseEntity<TarefaResponseDTO> alterarStatusTarefa(@PathVariable UUID id, @Valid @RequestBody AlterarStatusTarefaDTO alterarStatusDTO){
        return ResponseEntity.ok(tarefaServices.alterarStatusTarefa(id, alterarStatusDTO));
    }

    @DeleteMapping("/{id}") //DELETE /tarefa/{id}
    public ResponseEntity<Void> excluirTarefa(@PathVariable UUID id){
        tarefaServices.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
