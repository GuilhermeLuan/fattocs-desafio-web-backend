package dev.guilhermeluan.repository;

import dev.guilhermeluan.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrderByPresentationOrder();

    @Query("SELECT t FROM Task t WHERE t.taskName = :taskName AND t.id <> :id")
    Optional<Task> findByNameAndNotId(@Param("taskName") String taskName, @Param("id") Long id);

    Optional<Task> findByTaskName(String taskName);

}
