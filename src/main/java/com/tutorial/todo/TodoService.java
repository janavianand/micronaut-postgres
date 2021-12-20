package com.tutorial.todo;

import com.tutorial.todo.model.CustomError;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;

import java.util.*;

@Singleton
public class TodoService {

    private final ArrayList<String> todolist;
    private final Map<String, String> todoJson ;

    public TodoService() {
        this.todolist = new ArrayList<>();
        todolist.add("pick up");
        todolist.add("groceries");
        this.todoJson = new HashMap<>();
        todolist.forEach(todo->todoJson.put(todo,todo));
    }

    public Map<String, String> todoListJson(){
        return todoJson;
    }

    public ArrayList<String> todoListArray() {
        return todolist;
    }

    public Map<String, String> addTodo(String newTodo) {
        todolist.add(newTodo);
        todoJson.put(newTodo,newTodo);
        return todoJson;
    }

    public HttpResponse deleteTodo(String todo) {
        if(!todoJson.containsKey(todo)){
            final CustomError notFound = CustomError.builder().status(HttpStatus.NOT_FOUND.getCode()).error(HttpStatus.NOT_FOUND.name()).message("todo not found.").build();
            return HttpResponse.notFound(notFound);
        }
        todolist.remove(todo);
        todoJson.remove(todo);
        return HttpResponse.ok(todoJson);
    }
}
