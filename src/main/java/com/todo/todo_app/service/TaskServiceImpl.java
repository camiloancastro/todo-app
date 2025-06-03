package com.todo.todo_app.service;

import com.todo.todo_app.dto.NotificationDTO;
import com.todo.todo_app.dto.TaskPOJO;
import com.todo.todo_app.model.Task;
import com.todo.todo_app.repository.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements ITaskService{

  private final TaskRepository repository;
  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${notifications.service.url}")
  private String notificationsUrl;


  @Override
  public List<TaskPOJO> getAllTasks() {
    return repository.findAll().stream()
        .map(task -> new TaskPOJO(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted()))
        .collect(Collectors.toList());
  }

  @Override
  public Task getTaskById(Long id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
  }

  @Override
  public void createTask(String title, String description) {
    Task task = new Task();
    task.setTitle(title);
    task.setDescription(description);
    task.setCompleted(false);
    repository.save(task);

    // Notificar al microservicio de notificaciones
    NotificationDTO notification = new NotificationDTO();
    notification.setTaskId(task.getId());
    notification.setMessage("Nueva tarea creada: " + task.getTitle());
    notification.setCompleted(false);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<NotificationDTO> request = new HttpEntity<>(notification, headers);

    try {
      ResponseEntity<String> response = restTemplate.postForEntity(notificationsUrl, request, String.class);
      log.info("Notificación enviada: " + response.getStatusCode());
    } catch (Exception e) {
      log.error("Error al enviar notificación: " + e.getMessage());
    }

  }

  @Override
  public void updateTask(Long id, String title, String description, boolean completed) {
    Task task = getTaskById(id);
    task.setTitle(title);
    task.setDescription(description);
    task.setCompleted(completed);
    repository.save(task);
  }

  @Override
  public void deleteTask(Long id) {
    repository.deleteById(id);
  }

  @Override
  public void deleteAllTasks() {
    repository.deleteAll();
  }

  @Override
  public void markTaskAsCompleted(Long id) {
    Task task = getTaskById(id);
    task.setCompleted(true);
    repository.save(task);
  }

  @Override
  public void markTaskAsNotCompleted(Long id) {
    Task task = getTaskById(id);
    task.setCompleted(false);
    repository.save(task);
  }
}
