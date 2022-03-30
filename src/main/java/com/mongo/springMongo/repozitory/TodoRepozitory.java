package com.mongo.springMongo.repozitory;

import com.mongo.springMongo.model.TodoDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TodoRepozitory extends MongoRepository<TodoDto,String > {

    @Query("{'todo' : ?0}")
    Optional<TodoDto> findByTodo(String todo);


}
