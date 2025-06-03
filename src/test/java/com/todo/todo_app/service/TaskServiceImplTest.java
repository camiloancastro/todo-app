package com.todo.todo_app.service;

import com.todo.todo_app.model.Task;
import com.todo.todo_app.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
public class TaskServiceImplTest {

  @Mock
  private TaskRepository repository;

  @InjectMocks
  private TaskServiceImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createTask_shouldSaveTask() {
    service.createTask("Test Title", "Test Description");
    verify(repository, times(1)).save(any(Task.class));
  }

  @Test
  void getTaskById_shouldReturnTask() {
    Task task = new Task();
    task.setId(1L);
    task.setTitle("Test");

    when(repository.findById(1L)).thenReturn(Optional.of(task));

    Task result = service.getTaskById(1L);
    assertEquals("Test", result.getTitle());
  }

  @Test
  void markTaskAsCompleted_shouldUpdateTask() {
    Task task = new Task();
    task.setId(1L);
    task.setCompleted(false);

    when(repository.findById(1L)).thenReturn(Optional.of(task));

    service.markTaskAsCompleted(1L);

    assertTrue(task.isCompleted());
    verify(repository).save(task);
  }

}
