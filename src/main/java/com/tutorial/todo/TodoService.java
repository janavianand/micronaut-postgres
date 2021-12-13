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
}
