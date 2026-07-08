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

import com.task.task.models.Tarefa;
import com.task.task.services.TarefaServices;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    @Autowired
    private TarefaServices tarefaServices;

    @GetMapping //GET
    public ResponseEntity<List<Tarefa>> listarTarefas(){
        return ResponseEntity.ok(tarefaServices.listaTarefas());
    }

    @GetMapping("/{id}") //GET /tarefa/{id}
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable UUID id){
        return ResponseEntity.ok(tarefaServices.buscarTarefaPorId(id));
    }

    @PostMapping //POST /tarefa
    public ResponseEntity<Tarefa> salvarTarefa(@RequestBody Tarefa tarefa){
        return ResponseEntity.ok(tarefaServices.salvarTarefa(tarefa));
    }

    @PutMapping("/{id}") //PUT /tarefa/{id}
    public ResponseEntity<Tarefa> alterarTarefa(@PathVariable UUID id, @RequestBody Tarefa tarefa){
        tarefa.setId(id);
        return ResponseEntity.ok(tarefaServices.alterarTarefa(tarefa));
    }

    @DeleteMapping("/{id}") //DELETE /tarefa/{id}
    public ResponseEntity<Void> excluirTarefa(@PathVariable UUID id){
        tarefaServices.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
