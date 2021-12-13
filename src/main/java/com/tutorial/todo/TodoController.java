package com.tutorial.todo;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.*;

@Controller("/todo")
public class TodoController {
    private final TodoService todolist;

    public TodoController(TodoService todolist) {
        this.todolist = todolist;
    }

    @Get(value = "/",produces = MediaType.TEXT_PLAIN)
    public String getTodo (){
        return "Todo list";
    }

    @Get(value = "/array",produces = MediaType.APPLICATION_JSON)
    public ArrayList<String> getTodoArray (){
        return todolist.todoListArray();
    }

    @Get(value = "/json", produces = MediaType.APPLICATION_JSON)
    public Map<String, String> getTodoJson (){
        return todolist.todoListJson();
    }
}
