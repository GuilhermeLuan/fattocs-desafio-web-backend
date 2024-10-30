package dev.guilhermeluan.controller;

import dev.guilhermeluan.domain.Tarefa;
import dev.guilhermeluan.dtos.TarefaGetResponse;
import dev.guilhermeluan.dtos.TarefaPostRequest;
import dev.guilhermeluan.dtos.TarefaPostResponse;
import dev.guilhermeluan.service.TarefaService;
import dev.guilhermeluan.utils.TarefaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("/v1/tarefas")
public class Controller {
    private final TarefaService service;
    private final TarefaMapper mapper;

    @GetMapping
    public ResponseEntity<Page<TarefaGetResponse>> findAll(Pageable pageable) {

        Page<TarefaGetResponse> responses = service.findAll(pageable).map(mapper::toTarefaGetResponse);
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<TarefaPostResponse> insert(@RequestBody @Valid TarefaPostRequest request) {
        Tarefa tarefaToSave = mapper.toTarefa(request);

        Tarefa tarefaSaved = service.save(tarefaToSave);

        TarefaPostResponse response = mapper.toTarefaPostResponse(tarefaSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
