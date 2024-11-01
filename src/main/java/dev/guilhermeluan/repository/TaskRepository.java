package dev.guilhermeluan.repository;

import dev.guilhermeluan.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTaskName(String taskName);
}
