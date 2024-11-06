package dev.guilhermeluan.service;

import dev.guilhermeluan.domain.Task;
import dev.guilhermeluan.repository.TaskRepository;
import dev.guilhermeluan.utils.TaskUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService service;
    @Mock
    private TaskRepository repository;
    @Mock
    private EntityManager entityManager;
    private List<Task> tasksList;
    @Spy
    private TaskUtils tasksUtils;

    @BeforeEach
    void init() throws ParseException {
        tasksList = tasksUtils.newTaskList();
    }

    @Test
    @DisplayName("findAll returns a list of tasks")
    void findAll_ReturnsListOfTask_WhenSuccessful() {
        tasksList.sort(Comparator.comparingInt(Task::getPresentationOrder));
        BDDMockito.when(repository.findAllByOrderByPresentationOrder()).thenReturn(tasksList);

        var tasksFound = service.findAll();
        Assertions.assertThat(tasksFound).isNotNull().containsExactlyElementsOf(tasksList);
    }

    @Test
    @DisplayName("GET v1/tasks returns an empty list when no task is found")
    void findAll_ReturnsEmptyList_WhenNoTaskIsFound() {
        var tasksFound = service.findAll();
        Assertions.assertThat(tasksFound).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findById returns an tarefa with given id")
    void findById_ReturnsTaskById_WhenSuccessful() {
        var expectedTask = tasksList.getFirst();
        BDDMockito.when(repository.findById(expectedTask.getId())).thenReturn(Optional.of(expectedTask));

        var tasks = service.findByIdOrThrowNotFound(expectedTask.getId());

        Assertions.assertThat(tasks).isEqualTo(expectedTask);
    }

    @Test
    @DisplayName("findById throws ResponseStatusException when tarefa is not found")
    void findById_ThrowsResponseStatusException_WhenTaskIsNotFound() {
        var expectedTask = tasksList.getFirst();
        BDDMockito.when(repository.findById(expectedTask.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.findByIdOrThrowNotFound(expectedTask.getId()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("save creates an tarefa")
    void save_CreatesTask_WhenSuccessful() throws ParseException {

        BDDMockito.when(entityManager.createQuery(anyString()))
                .thenReturn(mock(Query.class));
        BDDMockito.when(entityManager.createQuery(anyString()).getSingleResult())
                .thenReturn(0);

        var tarefaToSave = tasksUtils.newTaskToSave();

        BDDMockito.when(repository.save(tarefaToSave)).thenReturn(tarefaToSave);
        BDDMockito.when(service.generateNextOrdemApresentacao()).thenReturn(1);

        var savedTask = service.save(tarefaToSave);

        Assertions.assertThat(savedTask).isEqualTo(tarefaToSave).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("delete removes an tarefa")
    void delete_RemoveTask_WhenSuccessful() {
        var tarefaToDelete = tasksList.getFirst();
        BDDMockito.when(repository.findById(tarefaToDelete.getId())).thenReturn(Optional.of(tarefaToDelete));

        Assertions.assertThatNoException().isThrownBy(() -> service.delete(tarefaToDelete.getId()));
    }

    @Test
    @DisplayName("delete throws ResponseStatusException when tarefa is not found")
    void delete_ThrowsResponseStatusException_WhenTaskIsNotFound() {
        var tarefaToDelete = tasksList.getFirst();
        BDDMockito.when(repository.findById(tarefaToDelete.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.delete(tarefaToDelete.getId()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("update updates an tarefa")
    void update_UpdatesTask_WhenSuccessful() {
        var tarefaToUpdate = tasksList.getFirst();
        tarefaToUpdate.setTaskName("Novo Nome Task");

        BDDMockito.when(repository.findById(tarefaToUpdate.getId())).thenReturn(Optional.of(tarefaToUpdate));
        BDDMockito.when(repository.save(tarefaToUpdate)).thenReturn(tarefaToUpdate);

        Assertions.assertThatNoException().isThrownBy(() -> service.update(tarefaToUpdate));
    }

    @Test
    @DisplayName("update throws ResponseStatusException when tarefa is not found")
    void update_ThrowsResponseStatusException_WhenProducerIsNotFound() {
        var tarefaToUpdate = tasksList.getFirst();

        BDDMockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.update(tarefaToUpdate))
                .isInstanceOf(ResponseStatusException.class);
    }
}