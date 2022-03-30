package com.mongo.springMongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {


    //Declare appropriate validators and validator event listeners

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(){
        return new ValidatingMongoEventListener(localValidatorFactoryBean());
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(){
        return new LocalValidatorFactoryBean();
    }

}
