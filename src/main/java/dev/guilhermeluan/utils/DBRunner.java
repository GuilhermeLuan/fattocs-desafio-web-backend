package dev.guilhermeluan.utils;

import dev.guilhermeluan.domain.Task;
import dev.guilhermeluan.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Slf4j

@Component
public class DBRunner implements CommandLineRunner {
    private final TaskRepository repository;

    @Override
    public void run(String... args) {
        repository.saveAll(List.of(
                Task.builder()
                        .taskName("Finalizar Back-end")
                        .cost(1500.50)
                        .dataLimit(Date.valueOf("2024-11-21"))
                        .presentationOrder(1)
                        .build()
        ));

        log.info("Tarefas cadastradas!");
    }

}
