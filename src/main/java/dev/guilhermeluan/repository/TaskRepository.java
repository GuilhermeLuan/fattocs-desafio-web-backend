package dev.guilhermeluan.repository;

import dev.guilhermeluan.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrderByPresentationOrder();
}
