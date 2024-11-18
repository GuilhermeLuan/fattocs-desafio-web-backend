package dev.guilhermeluan.repository;

import dev.guilhermeluan.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrderByPresentationOrder();

    Optional<Task> findByTaskName(String taskName);
}
