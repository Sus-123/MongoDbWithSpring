package com.mongo.springMongo.controller;
import com.mongo.springMongo.exception.CollectionException;
import com.mongo.springMongo.model.TodoDto;
import com.mongo.springMongo.repozitory.TodoRepozitory;
import com.mongo.springMongo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    private TodoRepozitory todoRepozitory;
    @Autowired
    private TodoService todoService;


    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos() {
        List<TodoDto> toDos = todoService.getTodo();
        return new ResponseEntity<>(toDos, toDos.size()>0 ?  HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/postTodos")
    public ResponseEntity<?> createTodos(@RequestBody TodoDto todoDto ) {
        try {
            todoService.createTodo(todoDto);
            return new ResponseEntity<>(todoDto, HttpStatus.OK);
        } catch (ConstraintViolationException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CollectionException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodos(@PathVariable("id") String id)  {
        try {
            TodoDto todoDto = todoService.getTodo(id);
            return  new ResponseEntity<>(todoDto, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateByTodo( @RequestBody TodoDto todoDto, @PathVariable("id") String id) {
        try{
            todoService.updateTodo(todoDto, id);
            return new ResponseEntity<>("Todo updated", HttpStatus.OK);
        } catch (ConstraintViolationException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CollectionException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }




    @DeleteMapping("/todos/Delete/{id}")
    public ResponseEntity<?> deletebyId(@PathVariable("id") String id) {
        Optional<TodoDto> optional = todoRepozitory.findById(id);
        if(optional.isPresent()){
            todoRepozitory.delete(optional.get());
            return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
        }
        else return new ResponseEntity<>("No todos available with id " + id , HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/todos/DeleteById/{id}")
    public ResponseEntity<?> deleteby(@PathVariable("id") String id) {

        try{
            todoRepozitory.deleteById(id);
            return new ResponseEntity<>("deleted successfully", HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
