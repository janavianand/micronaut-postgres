package com.tutorial.todo;

import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Controller("/todo")
public class TodoController {
    private final TodoService todolist;
    private final Logger Log = LoggerFactory.getLogger(TodoController.class);

    public TodoController(TodoService todolist) {
        this.todolist = todolist;
    }

    @Operation(summary = "Returns all available todo as an  array")
    @ApiResponse(
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Tag(name = "Todo Array")
    @Get(value = "/array",produces = MediaType.APPLICATION_JSON)
    public ArrayList<String> getTodoArray (){
        return todolist.todoListArray();
    }

    @Operation(summary = "Returns all available todo as JSON")
    @ApiResponse(
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Tag(name = "Todo")
    @Get(value = "/json", produces = MediaType.APPLICATION_JSON)
    public Map<String, String> getTodoJson (){
        return todolist.todoListJson();
    }

    @Operation(summary = "Add a todo")
    @ApiResponse(
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Tag(name = "Todo")
    @Put(value = "/", produces = MediaType.APPLICATION_JSON,consumes = MediaType.TEXT_PLAIN)
    public Map<String, String> addTodoJson (@Body String todo){
        Log.info("Put request to update todo");
        return todolist.addTodo(todo);
    }

    @Operation(summary = "Delete a todo")
    @ApiResponse(
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Tag(name = "Todo")
    @Delete(value = "/{removeTodo}", produces = MediaType.APPLICATION_JSON,consumes = MediaType.TEXT_PLAIN)
    public HttpResponse deleteTodoJson (@PathVariable String removeTodo){
        return todolist.deleteTodo(removeTodo);
    }

    @Operation(summary = "Todo string introduction")
    @ApiResponse(
            content = @Content(mediaType = MediaType.TEXT_PLAIN)
    )
    @Tag(name = "Todo")
    @Get(value = "/",produces = MediaType.TEXT_PLAIN)
    public String getTodo (){
        Log.info("Get request todo");
        return "Todo list";
    }
}
