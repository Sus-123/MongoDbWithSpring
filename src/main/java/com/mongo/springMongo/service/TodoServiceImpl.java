package com.mongo.springMongo.service;
import com.mongo.springMongo.exception.CollectionException;
import com.mongo.springMongo.model.TodoDto;
import com.mongo.springMongo.repozitory.TodoRepozitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepozitory todoRepozitory;

    @Override
    public void createTodo(TodoDto todo) throws CollectionException {
        Optional<TodoDto> todoDtoOptional = todoRepozitory.findByTodo(todo.getTodo());
        if(todoDtoOptional.isPresent()){
            throw new CollectionException(CollectionException.alreadyExistException(todoDtoOptional.get().getTodo()));
        } else {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepozitory.save(todo);
        }

    }

    @Override
    public void updateTodo(TodoDto todoDto, String id) throws CollectionException {
        Optional<TodoDto> optional = todoRepozitory.findById(id);
        if(!optional.isPresent()){
            throw new CollectionException(CollectionException.NotFoundException(todoDto.getId()));
        } else {
            TodoDto updated = optional.get();
            updated.setTodo(todoDto.getTodo() != null ? todoDto.getTodo() : updated.getTodo()) ;
            updated.setDescription(todoDto.getDescription() != null ? todoDto.getDescription() : updated.getDescription());
            updated.setCompleted(todoDto.getCompleted() != null ? todoDto.getCompleted() : updated.getCompleted());updated.setUpdatedAt(todoDto.getUpdatedAt() != null ? todoDto.getUpdatedAt() : updated.getUpdatedAt());
            updated.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepozitory.save(updated);
        }
    }

    @Override
    public List<TodoDto> getTodo()  {
        List<TodoDto> todoDto = todoRepozitory.findAll();
        if(todoDto.size() > 0){
            return todoDto;
        } else{
            return new ArrayList<>();
        }
    }

    @Override
    public TodoDto getTodo(String id) throws CollectionException {
        Optional<TodoDto> todoDtoOptional = todoRepozitory.findById(id);
        if(todoDtoOptional.isPresent()){
            return todoDtoOptional.get();
        } else {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteTodo(String id) throws CollectionException {
        Optional<TodoDto> todoDtoOptional = todoRepozitory.findById(id);
        if(todoDtoOptional.isPresent()){
            todoRepozitory.deleteById(id);
        } else {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }

    }
}
