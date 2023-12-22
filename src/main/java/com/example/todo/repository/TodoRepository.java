// Write your code here
package com.example.todo.repository;

import com.example.todo.model.Todo;
import java.util.*;

public interface TodoRepository {

    public ArrayList<Todo> getAllTodo();

    public Todo addTodo(Todo todo);

    public Todo getTodoById(int todoId);

    public Todo updateTodo(int todoId, Todo todo);

    public void deleteTodo(int todoId);
}