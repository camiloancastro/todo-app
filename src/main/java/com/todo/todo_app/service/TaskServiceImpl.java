package com.todo.todo_app.service;

import com.todo.todo_app.dto.TaskPOJO;
import com.todo.todo_app.model.Task;
import com.todo.todo_app.repository.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements ITaskService{

  private final TaskRepository repository;

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
