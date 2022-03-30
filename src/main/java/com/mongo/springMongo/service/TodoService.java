package com.mongo.springMongo.service;
import com.mongo.springMongo.exception.CollectionException;
import com.mongo.springMongo.model.TodoDto;

import java.util.List;

public interface TodoService {

    public void createTodo(TodoDto todo) throws CollectionException;
    public void updateTodo(TodoDto todo, String id) throws CollectionException;
    public List<TodoDto> getTodo() ;
    public TodoDto getTodo(String id) throws CollectionException;
    public void deleteTodo(String id) throws CollectionException;





}
