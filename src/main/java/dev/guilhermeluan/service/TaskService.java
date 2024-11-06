package dev.guilhermeluan.service;

import dev.guilhermeluan.domain.Task;
import dev.guilhermeluan.exception.NotFoundException;
import dev.guilhermeluan.repository.TaskRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class TaskService {
    private final TaskRepository repository;

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Task> findAll() {
        return repository.findAllByOrderByPresentationOrder();
    }

    public Task findByIdOrThrowNotFound(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
    }

    @Transactional
    public Task save(Task task) {
        task.setPresentationOrder(generateNextOrdemApresentacao());
        return repository.save(task);
    }

    public void update(Task taskToUpdate) {
        Task taskFound = findByIdOrThrowNotFound(taskToUpdate.getId());
        taskToUpdate.setPresentationOrder(taskFound.getPresentationOrder());
        repository.save(taskToUpdate);
    }

    public void delete(Long id) {
        assertTaskExists(id);
        repository.deleteById(id);
    }

    public void assertTaskExists(Long id) {
        findByIdOrThrowNotFound(id);
    }

    @Transactional
    public Integer generateNextOrdemApresentacao() {
        Integer maxOrdem = (Integer) entityManager
                .createQuery("SELECT COALESCE(MAX(t.presentationOrder), 0) FROM Task t")
                .getSingleResult();
        return maxOrdem + 1;
    }
}
