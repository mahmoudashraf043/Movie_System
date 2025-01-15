package com.Mahmoud.Movie_System.exceptionHandling;

public class WrongDataException  extends RuntimeException{
    public WrongDataException(String message){
        super(message);
    }
}
