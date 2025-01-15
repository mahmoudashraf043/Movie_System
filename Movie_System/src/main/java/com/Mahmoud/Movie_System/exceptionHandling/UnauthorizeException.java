package com.Mahmoud.Movie_System.exceptionHandling;

public class UnauthorizeException extends RuntimeException{

    public UnauthorizeException(String message){
        super(message);
    }
}
