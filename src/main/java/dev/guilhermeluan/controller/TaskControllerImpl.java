package dev.guilhermeluan.controller;

import dev.guilhermeluan.domain.Task;
import dev.guilhermeluan.dtos.TaskGetResponse;
import dev.guilhermeluan.dtos.TaskPostRequest;
import dev.guilhermeluan.dtos.TaskPostResponse;
import dev.guilhermeluan.dtos.TaskPutRequest;
import dev.guilhermeluan.repository.TaskRepository;
import dev.guilhermeluan.repository.UserRepository;
import dev.guilhermeluan.service.TaskService;
import dev.guilhermeluan.utils.TaskMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/v1/tasks")
public class TaskControllerImpl implements TaskController {
    private final TaskService service;
    private final TaskMapper mapper;

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @GetMapping("/user")
    @Override
    public ResponseEntity<List<TaskGetResponse>> findByUserId(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = userRepository.findByEmail(userDetails.getUsername()).getId();
        List<Task> tasks = taskRepository.findByUserId(userId);

        List<TaskGetResponse> responses = mapper.toTaskGetResponse(tasks);

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @Override
    @PostMapping("/user")
    public ResponseEntity<TaskPostResponse> insertUserTask(@AuthenticationPrincipal UserDetails userDetails, @RequestBody TaskPostRequest request) {
        Task taskToSave = mapper.toTask(request);
        taskToSave.setUser(userRepository.findByEmail(userDetails.getUsername()));

        Task taskSaved = service.save(taskToSave);

        TaskPostResponse response = mapper.toTaskPostResponse(taskSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    @Override
    public ResponseEntity<Void> update(@RequestBody @Valid TaskPutRequest request) {
        Task taskToUpdate = mapper.toTask(request);

        service.update(taskToUpdate);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
