package dev.guilhermeluan.controller;

import dev.guilhermeluan.domain.Task;
import dev.guilhermeluan.dtos.TaskGetResponse;
import dev.guilhermeluan.dtos.TaskPostRequest;
import dev.guilhermeluan.dtos.TaskPostResponse;
import dev.guilhermeluan.dtos.TaskPutRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Task", description = "Esta é uma API de gerenciamento de Tarefas desenvolvida com Spring")
public interface TaskController {

    @GetMapping
    ResponseEntity<List<TaskGetResponse>> findByUserId(@AuthenticationPrincipal UserDetails userDetails);

    @PostMapping
    @Operation(summary = "Adiciona uma nova Tarefa", description = "Adiciona uma nova Tarefa", tags = {"Task"}, responses = {
            @ApiResponse(description = "Success", responseCode = "201",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Task.class))
                            )
                    }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    ResponseEntity<TaskPostResponse> insertUserTask(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid TaskPostRequest request);

    @PutMapping
    @Operation(summary = "Atualiza uma Tarefa", description = "Atualiza uma Tarefa", tags = {"Task"}, responses = {
            @ApiResponse(description = "Success", responseCode = "204",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Task.class))
                            )
                    }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    ResponseEntity<Void> update(@RequestBody @Valid TaskPutRequest request);

    @DeleteMapping("/{id}")
    @PutMapping
    @Operation(summary = "Deleta uma Tarefa", description = "Deleta uma Tarefa", tags = {"Task"}, responses = {
            @ApiResponse(description = "Success", responseCode = "204",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Task.class))
                            )
                    }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    ResponseEntity<Void> delete(@PathVariable Long id);
}
