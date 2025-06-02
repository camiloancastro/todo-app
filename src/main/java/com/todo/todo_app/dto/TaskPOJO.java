package com.todo.todo_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskPOJO {

  private Long id;
  private String title;
  private String description;
  private boolean completed;

  public TaskPOJO(Long id, String title, String description, boolean completed) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.completed = completed;
  }

}
