package dev.guilhermeluan.controller;

import dev.guilhermeluan.domain.Tarefa;
import dev.guilhermeluan.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping("/v1/tarefas")
public class Controller {
    private final TarefaService service;

    @GetMapping
    public ResponseEntity<Page<Tarefa>> findAll(Pageable pageable) {
        Page<Tarefa> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }
}
