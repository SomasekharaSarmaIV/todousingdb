/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.todo.model.Todo;
import com.example.todo.model.TodoRowMapper;
import com.example.todo.repository.TodoRepository;

// Write your code here
@Service
public class TodoH2Service implements TodoRepository {
  @Autowired
  private JdbcTemplate db;

  @Override
  public ArrayList<Todo> getAllTodo() {
    List<Todo> es = db.query("select * from TODOLIST", new TodoRowMapper());
    ArrayList<Todo> e = new ArrayList<>(es);
    return e;
  }

  @Override
  public Todo addTodo(Todo todo) {
    db.update("insert into TODOLIST(todo,status,priority) values(?,?,?)", todo.getTodo(),
        todo.getStatus(), todo.getPriority());
    Todo se = db.queryForObject("select * from TODOLIST where todo=? and status=? and priority=?",
        new TodoRowMapper(), todo.getTodo(), todo.getStatus(), todo.getPriority());

    return se;
  }

  @Override
  public Todo getTodoById(int todoId) {
    try {
      return db.queryForObject("select * from TODOLIST where id=?", new TodoRowMapper(), todoId);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public Todo updateTodo(int todoId, Todo todo) {
    if (todo.getTodo() != null) {
      db.update("update TODOLIST set todo=? where id=?", todo.getTodo(), todoId);
    }
    if (todo.getStatus() != null) {
      db.update("update TODOLIST set status=? where id=?", todo.getStatus(), todoId);
    }
    if (todo.getPriority() != null) {
      db.update("update TODOLIST set priority=? where id=?", todo.getPriority(), todoId);
    }
    return getTodoById(todoId);
  }

  @Override
  public void deleteTodo(int todoId) {
    db.update("delete from TODOLIST where id=?", todoId);
  }

}