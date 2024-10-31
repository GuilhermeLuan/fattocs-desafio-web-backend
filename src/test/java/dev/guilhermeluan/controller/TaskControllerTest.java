package dev.guilhermeluan.controller;

import dev.guilhermeluan.domain.Task;
import dev.guilhermeluan.exception.NotFoundException;
import dev.guilhermeluan.repository.TaskRepository;
import dev.guilhermeluan.service.TaskService;
import dev.guilhermeluan.utils.FileUtils;
import dev.guilhermeluan.utils.TaskUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TaskController.class)
@AutoConfigureMockMvc
@ComponentScan(basePackages = {"dev.guilhermeluan"})
class TaskControllerTest {
    private static final String URL = "/v1/tasks";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskRepository repository;
    @MockBean
    private TaskService service;
    private List<Task> tasksList;
    @Autowired
    private TaskUtils taskUtils;
    @Autowired
    private FileUtils fileUtils;

    @BeforeEach
    void init() throws ParseException {
        tasksList = taskUtils.newTaskList();
    }

    @Test
    @DisplayName("GET v1/tasks returns a paginated list of tasks")
    void findAll_ReturnsPaginatedTasks_WhenSuccessful() throws Exception {
        var response = fileUtils.readResourceFile("task/get-task-200.json");
        var pageRequest = PageRequest.of(0, tasksList.size());
        var pageTask = new PageImpl<>(tasksList, pageRequest, tasksList.size());

        BDDMockito.when(service.findAll(BDDMockito.any(Pageable.class))).thenReturn(pageTask);

        mockMvc.perform(get(URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    @DisplayName("POST v1/tasks creates a wtask")
    void save_CreatesTask_WhenSuccessful() throws Exception {
        var request = fileUtils.readResourceFile("task/post-request-task-200.json");
        var response = fileUtils.readResourceFile("task/post-response-task-200.json");
        var taskToSave = taskUtils.newTaskToSave();

        BDDMockito.when(service.save(ArgumentMatchers.any())).thenReturn(taskToSave);

        mockMvc.perform(post(URL)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(response));
    }

    @Test
    @DisplayName("DELETE v1/tasks/1 removes a task")
    void delete_RemoveTask_WhenSuccessful() throws Exception {
        var expectedTask = tasksList.getFirst();
        var id = expectedTask.getId();

        BDDMockito.when(service.findByIdOrThrowNotFound(id)).thenReturn(expectedTask);

        mockMvc.perform(delete(URL + "/{id}", id))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE v1/tasks/99 throws NotFound when Task is not found")
    void delete_ThrowsNotFound_WhenTaskIsNotFound() throws Exception {
        var response = fileUtils.readResourceFile("task/delete-request-task-404.json");
        Long id = 99L;


        BDDMockito.willThrow(new NotFoundException("Tarefa não foi encontrada!"))
                .given(service).delete(ArgumentMatchers.anyLong());


        mockMvc.perform(delete(URL + "/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(response));
    }

    @Test
    @DisplayName("PUT v1/tasks updates a task")
    void update_UpdatesTask_WhenSuccessful() throws Exception {
        var request = fileUtils.readResourceFile("task/put-request-task-200.json");
        var taskToUpdate = tasksList.getFirst();

        BDDMockito.when(service.findByIdOrThrowNotFound(taskToUpdate.getId())).thenReturn(taskToUpdate);


        mockMvc.perform(put(URL)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("PUT v1/users throws NotFound when user is not found")
    void update_ThrowsNotFound_WhenTaskIsNotFound() throws Exception {
        var request = fileUtils.readResourceFile("task/put-request-task-404.json");
        var response = fileUtils.readResourceFile("task/put-response-task-404.json");

        BDDMockito.willThrow(new NotFoundException("Tarefa não foi encontrada!"))
                .given(service).update(ArgumentMatchers.any(Task.class));


        mockMvc.perform(put(URL)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(response));
    }

    @ParameterizedTest
    @MethodSource("postTaskBadRequestSource")
    @DisplayName("POST v1/task returns bad request when fields are invalid")
    void save_ReturnsBadRequest_WhenFieldsAreInvalid(String fileName, List<String> errors) throws Exception {
        var request = fileUtils.readResourceFile("task/%s".formatted(fileName));

        var mvcResult = mockMvc.perform(post(URL)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        var resolvedException = mvcResult.getResolvedException();

        Assertions.assertThat(resolvedException).isNotNull();

        Assertions.assertThat(resolvedException.getMessage()).contains(errors);
    }

    @ParameterizedTest
    @MethodSource("putTaskBadRequestSource")
    @DisplayName("PUT v1/task returns bad request when fields are invalid")
    void update_ReturnsBadRequest_WhenFieldsAreInvalid(String fileName, List<String> errors) throws Exception {
        var request = fileUtils.readResourceFile("task/%s".formatted(fileName));

        var mvcResult = mockMvc.perform(put(URL)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        var resolvedException = mvcResult.getResolvedException();

        Assertions.assertThat(resolvedException).isNotNull();

        Assertions.assertThat(resolvedException.getMessage()).contains(errors);
    }


    private static Stream<Arguments> putTaskBadRequestSource() {
        var allRequiredErrors = allRequiredErrors();
        allRequiredErrors.add("The field 'id' is required");

        return Stream.of(
                Arguments.of("put-request-task-empty-fields-400.json", allRequiredErrors),
                Arguments.of("put-request-task-blank-fields-400.json", allRequiredErrors)
                );
    }

    private static Stream<Arguments> postTaskBadRequestSource() {
        var allRequiredErrors = allRequiredErrors();

        return Stream.of(
                Arguments.of("post-request-task-empty-fields-400.json", allRequiredErrors),
                Arguments.of("post-request-task-blank-fields-400.json", allRequiredErrors)
                );
    }


    private static List<String> allRequiredErrors() {
        var firstNameRequiredError = "The field 'taskName' is required";
        var lastNameRequiredError = "The field 'cost' is required";
        var emailRequiredError = "The field 'dataLimit' is required";

        return new ArrayList<>(List.of(firstNameRequiredError, lastNameRequiredError, emailRequiredError));
    }
}