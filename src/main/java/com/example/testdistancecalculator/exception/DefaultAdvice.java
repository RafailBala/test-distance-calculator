package com.example.testdistancecalculator.exception;

import com.example.testdistancecalculator.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(DistanceNotFoundException.class)
    public ResponseEntity<Response> handleException(DistanceNotFoundException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        Response response = new Response(message, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
