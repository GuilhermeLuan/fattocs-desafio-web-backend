package dev.guilhermeluan.controller;

import dev.guilhermeluan.dtos.TarefaGetResponse;
import dev.guilhermeluan.service.TarefaService;
import dev.guilhermeluan.utils.TarefaMapper;
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
    private final TarefaMapper mapper;

    @GetMapping
    public ResponseEntity<Page<TarefaGetResponse>> findAll(Pageable pageable) {

        Page<TarefaGetResponse> responses = service.findAll(pageable).map(mapper::toTarefaGetResponse);
        return ResponseEntity.ok(responses);
    }
}
