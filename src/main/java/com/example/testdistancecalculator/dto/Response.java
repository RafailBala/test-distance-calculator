package com.example.testdistancecalculator.dto;

import lombok.Data;

@Data
public class Response {

    private String message;
    private String message1;
    public Response() {
    }

    public Response(String message, String message1) {
        this.message = message;
        this.message1=message1;
    }
}