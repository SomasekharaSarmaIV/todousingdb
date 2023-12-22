/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 */

// Write your code here

package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.todo.model.Todo;
import com.example.todo.service.TodoH2Service;

import java.util.*;

// Write your code here
@RestController
public class TodoController {
  @Autowired
  private TodoH2Service es;

  @GetMapping("/todos")
  public ArrayList<Todo> getAllTodo() {
    return es.getAllTodo();
  }

  @PostMapping("/todos")
  public Todo addTodo(@RequestBody Todo todo) {
    return es.addTodo(todo);
  }

  @GetMapping("/todos/{todoId}")
  public Todo getTodoById(@PathVariable("todoId") int todoId) {

    return es.getTodoById(todoId);
  }

  @PutMapping("/todos/{todoId}")
  public Todo updateTodo(@PathVariable("todoId") int todoId, @RequestBody Todo todo) {
    return es.updateTodo(todoId, todo);
  }

  @DeleteMapping("/todos/{todoId}")
  public void deleteTodo(@PathVariable("todoId") int todoId) {
    es.deleteTodo(todoId);
  }
}
