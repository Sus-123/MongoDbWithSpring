package com.mongo.springMongo.exception;

public class CollectionException extends Exception {

    public CollectionException(String message){
        super(message);
    }

    public static String NotFoundException(String id){
        return  "Todo with id " + id + " is not found";
    }

    public static String alreadyExistException(String id){
        return  "Todo with id " + id + " already exist";
    }








}
