package com.mongo.springMongo.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class TodoDto {

    @Id
    private String id;

    @NotNull(message = "work can not be null")
    private String todo;

    @NotNull(message = "description can not be null")
    private String description;

    @NotNull(message = "completed can not be null")
    private Boolean completed;

    private Date createdAt;
    private Date updatedAt;

}
