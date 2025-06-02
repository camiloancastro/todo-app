package com.todo.todo_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.todo.todo_app.model.Task;
import com.todo.todo_app.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoAppIntegrationTest {

  @Autowired
  private TaskRepository repository;

  @Test
  void testCreateAndFindTask() {
    Task task = new Task();
    task.setTitle("Integration Test");
    task.setDescription("Description");
    task.setCompleted(false);

    Task saved = repository.save(task);

    assertNotNull(saved.getId());

    Task found = repository.findById(saved.getId()).orElseThrow();
    assertEquals("Integration Test", found.getTitle());
  }

}
