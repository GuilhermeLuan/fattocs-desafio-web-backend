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
    private List<Task> tarefasList;
    @Spy
    private TaskUtils tarefasUtils;

    @BeforeEach
    void init() throws ParseException {
        tarefasList = tarefasUtils.newTaskList();
    }

    @Test
    @DisplayName("findAll returns a paginated list of tarefas")
    void findAll_ReturnsPaginatedTarefa_WhenSuccessful() {
        var pageRequest = PageRequest.of(0, tarefasList.size());
        var pageTarefa = new PageImpl<Task>(tarefasList, pageRequest, 1);

        BDDMockito.when(repository.findAll(BDDMockito.any(Pageable.class))).thenReturn(pageTarefa);

        var tarefasFound = service.findAll(pageRequest);
        Assertions.assertThat(tarefasFound).isNotNull().hasSameElementsAs(tarefasList);
    }

    @Test
    @DisplayName("findById returns an tarefa with given id")
    void findById_ReturnsTarefaById_WhenSuccessful() {
        var expectedTarefa = tarefasList.getFirst();
        BDDMockito.when(repository.findById(expectedTarefa.getId())).thenReturn(Optional.of(expectedTarefa));

        var tarefas = service.findByIdOrThrowNotFound(expectedTarefa.getId());

        Assertions.assertThat(tarefas).isEqualTo(expectedTarefa);
    }

    @Test
    @DisplayName("findById throws ResponseStatusException when tarefa is not found")
    void findById_ThrowsResponseStatusException_WhenTarefaIsNotFound() {
        var expectedTarefa = tarefasList.getFirst();
        BDDMockito.when(repository.findById(expectedTarefa.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.findByIdOrThrowNotFound(expectedTarefa.getId()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("save creates an tarefa")
    void save_CreatesTarefa_WhenSuccessful() throws ParseException {

        BDDMockito.when(entityManager.createQuery(anyString()))
                .thenReturn(mock(Query.class));
        BDDMockito.when(entityManager.createQuery(anyString()).getSingleResult())
                .thenReturn(0);

        var tarefaToSave = tarefasUtils.newTaskToSave();

        BDDMockito.when(repository.save(tarefaToSave)).thenReturn(tarefaToSave);
        BDDMockito.when(service.generateNextOrdemApresentacao()).thenReturn(1);

        var savedTarefa = service.save(tarefaToSave);

        Assertions.assertThat(savedTarefa).isEqualTo(tarefaToSave).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("delete removes an tarefa")
    void delete_RemoveTarefa_WhenSuccessful() {
        var tarefaToDelete = tarefasList.getFirst();
        BDDMockito.when(repository.findById(tarefaToDelete.getId())).thenReturn(Optional.of(tarefaToDelete));

        Assertions.assertThatNoException().isThrownBy(() -> service.delete(tarefaToDelete.getId()));
    }

    @Test
    @DisplayName("delete throws ResponseStatusException when tarefa is not found")
    void delete_ThrowsResponseStatusException_WhenTarefaIsNotFound() {
        var tarefaToDelete = tarefasList.getFirst();
        BDDMockito.when(repository.findById(tarefaToDelete.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.delete(tarefaToDelete.getId()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("update updates an tarefa")
    void update_UpdatesTarefa_WhenSuccessful() {
        var tarefaToUpdate = tarefasList.getFirst();
        tarefaToUpdate.setTaskName("Novo Nome Tarefa");

        BDDMockito.when(repository.findById(tarefaToUpdate.getId())).thenReturn(Optional.of(tarefaToUpdate));
        BDDMockito.when(repository.save(tarefaToUpdate)).thenReturn(tarefaToUpdate);

        Assertions.assertThatNoException().isThrownBy(() -> service.update(tarefaToUpdate));
    }

    @Test
    @DisplayName("update throws ResponseStatusException when tarefa is not found")
    void update_ThrowsResponseStatusException_WhenProducerIsNotFound() {
        var tarefaToUpdate = tarefasList.getFirst();

        BDDMockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.update(tarefaToUpdate))
                .isInstanceOf(ResponseStatusException.class);
    }
}