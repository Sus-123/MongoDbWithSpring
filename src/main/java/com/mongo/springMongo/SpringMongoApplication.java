package com.mongo.springMongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mongo.springMongo")
public class SpringMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoApplication.class, args);
	}

}
