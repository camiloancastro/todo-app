package com.todo.todo_app.service;

import com.todo.todo_app.dto.TaskPOJO;
import com.todo.todo_app.model.Task;
import java.util.List;

public interface ITaskService {

    List<TaskPOJO> getAllTasks();

    Task getTaskById(Long id);

    void createTask(String title, String description);

    void updateTask(Long id, String title, String description, boolean completed);

    void deleteTask(Long id);

    void deleteAllTasks();

    void markTaskAsCompleted(Long id);

    void markTaskAsNotCompleted(Long id);

}
