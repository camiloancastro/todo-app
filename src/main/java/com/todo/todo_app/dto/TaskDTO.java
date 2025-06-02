package com.todo.todo_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

  private String title;
  private String description;
  private boolean completed;

}
