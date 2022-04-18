package com.example.testdistancecalculator.dto;

public class Response {

    private String message;
    private String message1;
    public Response() {

    }
    public Response(String message) {
        this.message=message;
    }

    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public Response(String message, String message1) {
        this.message = message;
        this.message1=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}