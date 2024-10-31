package dev.guilhermeluan.controller;

import dev.guilhermeluan.domain.Task;
import dev.guilhermeluan.dtos.TaskGetResponse;
import dev.guilhermeluan.dtos.TaskPostRequest;
import dev.guilhermeluan.dtos.TaskPostResponse;
import dev.guilhermeluan.dtos.TaskPutRequest;
import dev.guilhermeluan.service.TaskService;
import dev.guilhermeluan.utils.TaskMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    private final TaskService service;
    private final TaskMapper mapper;

    @GetMapping
    public ResponseEntity<Page<TaskGetResponse>> findAll(Pageable pageable) {

        Page<TaskGetResponse> responses = service.findAll(pageable).map(mapper::toTaskGetResponse);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping
    public ResponseEntity<TaskPostResponse> insert(@RequestBody @Valid TaskPostRequest request) {
        Task taskToSave = mapper.toTask(request);

        Task taskSaved = service.save(taskToSave);

        TaskPostResponse response = mapper.toTaskPostResponse(taskSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid TaskPutRequest request) {
        Task taskToUpdate = mapper.toTask(request);

        service.update(taskToUpdate);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
