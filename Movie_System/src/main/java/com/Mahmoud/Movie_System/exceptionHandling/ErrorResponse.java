package com.Mahmoud.Movie_System.exceptionHandling;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {


    private int code;

    private String message;

    private String details;





}
