package com.todo.todo_app.controller;

import com.todo.todo_app.dto.TaskDTO;
import com.todo.todo_app.dto.TaskPOJO;
import com.todo.todo_app.model.Task;
import com.todo.todo_app.service.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

  private final ITaskService service;

  @GetMapping
  public List<TaskPOJO> getAll() {
    return service.getAllTasks();
  }

  @GetMapping("/{id}")
  public Task getById(@PathVariable Long id) {
    return service.getTaskById(id);
  }

  @PostMapping
  public void create(@RequestBody TaskDTO dto) {
    service.createTask(dto.getTitle(), dto.getDescription());
  }

  @PutMapping("/{id}")
  public void update(@PathVariable Long id, @RequestBody TaskDTO dto) {
    service.updateTask(id, dto.getTitle(), dto.getDescription(), dto.isCompleted());
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    service.deleteTask(id);
  }

  @DeleteMapping("/all")
  public void deleteAll() {
    service.deleteAllTasks();
  }

  @PutMapping("/{id}/complete")
  public void markAsCompleted(@PathVariable Long id) {
    service.markTaskAsCompleted(id);
  }

  @PutMapping("/{id}/uncomplete")
  public void markAsNotCompleted(@PathVariable Long id) {
    service.markTaskAsNotCompleted(id);
  }

}
