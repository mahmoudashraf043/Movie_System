package com.Mahmoud.Movie_System.exceptionHandling;

public class NotFoundException extends  RuntimeException{

    public NotFoundException(String message){
        super(message);
    }
}
