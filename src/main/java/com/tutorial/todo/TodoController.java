package com.tutorial.todo;

import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.*;

import java.util.*;

@Controller("/todo")
public class TodoController {
    private final TodoService todolist;

    public TodoController(TodoService todolist) {
        this.todolist = todolist;
    }

    @Get(value = "/array",produces = MediaType.APPLICATION_JSON)
    public ArrayList<String> getTodoArray (){
        return todolist.todoListArray();
    }

    @Get(value = "/json", produces = MediaType.APPLICATION_JSON)
    public Map<String, String> getTodoJson (){
        return todolist.todoListJson();
    }

    @Put(value = "/", produces = MediaType.APPLICATION_JSON,consumes = MediaType.TEXT_PLAIN)
    public Map<String, String> addTodoJson (@Body String todo){
        return todolist.addTodo(todo);
    }

    @Delete(value = "/{removeTodo}", produces = MediaType.APPLICATION_JSON,consumes = MediaType.TEXT_PLAIN)
    public Map<String, String> deleteTodoJson (@PathVariable String removeTodo){
        return todolist.deleteTodo(removeTodo);
    }

    @Get(value = "/",produces = MediaType.TEXT_PLAIN)
    public String getTodo (){
        return "Todo list";
    }
}
