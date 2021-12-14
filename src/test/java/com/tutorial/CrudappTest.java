package com.tutorial;

import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import java.util.*;

import static io.micronaut.http.HttpRequest.*;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class TodoControllerTest {

    @Inject
    @Client("/todo")
    HttpClient client;
    private ArrayList<String> expectedArray = new ArrayList<>(
            List.of("pick up","groceries"));
    private Map<String, String> expectedJson = Map.of("pick up","pick up","groceries","groceries");

    @Test
    void getTodoTest1() {
        var response = client.toBlocking().retrieve("/");
        assertEquals("Todo list",response);
    }

    @Test
    void getTodoTest2() {
        var response = client.toBlocking().exchange("/",String.class);
        assertEquals("Todo list",response.body());
    }

    @Test
    void getTodoTest3() {

        var response = client.toBlocking().retrieve("/array",ArrayList.class);
        assertEquals(expectedArray,response);
    }

    @Test
    void getTodoTest4() {
        var response = client.toBlocking().retrieve("/json",Map.class);
        assertEquals(expectedJson,response);
    }

    @Test
    void putTodoTest() {
        var response = client.toBlocking().retrieve(PUT("/","laundry").contentType(MediaType.TEXT_PLAIN), Map.class);
        Map<String, String> newJson = Map.of("pick up","pick up","groceries","groceries","laundry","laundry");
        assertEquals(newJson,response);
    }

    @Test
    void deleteTodoTest() {
        var response = client.toBlocking().retrieve(DELETE("/laundry").contentType(MediaType.TEXT_PLAIN),Map.class);
        assertEquals(expectedJson,response);
    }
}
