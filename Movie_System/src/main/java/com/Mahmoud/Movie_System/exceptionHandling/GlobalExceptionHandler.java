package com.Mahmoud.Movie_System.exceptionHandling;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;

@ControllerAdvice

public class GlobalExceptionHandler {

    // Handle specific exceptions

    @ExceptionHandler(NotFoundException.class)

    public ResponseEntity<?> handleResourceNotFoundException(NotFoundException ex, WebRequest request) {

        ErrorResponse errorDetails = new ErrorResponse(

                HttpStatus.NOT_FOUND.value(),

                ex.getMessage(),

                request.getDescription(false)

        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }

    // Handle generic exceptions

    @ExceptionHandler(WrongDataException.class)

    public ResponseEntity<?> handleWrongDataException(WrongDataException ex, WebRequest request) {

        ErrorResponse errorDetails = new ErrorResponse(

                HttpStatus.BAD_REQUEST.value(),

                ex.getMessage(),

                request.getDescription(false)

        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(UnauthorizeException.class)

    public ResponseEntity<?> handleUnauthorizeException(UnauthorizeException ex, WebRequest request) {

        ErrorResponse errorDetails = new ErrorResponse(

                HttpStatus.UNAUTHORIZED.value(),

                ex.getMessage(),

                request.getDescription(false)

        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}

 