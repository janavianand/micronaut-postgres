package com.tutorial.todo;

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

    public Map<String, String> deleteTodo(String todo) {
        if(!todoJson.containsKey(todo)){
            return todoJson;
        }
        todolist.remove(todo);
        todoJson.remove(todo);
        return todoJson;
    }
}
