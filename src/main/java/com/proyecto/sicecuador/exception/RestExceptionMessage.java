package com.proyecto.sicecuador.exception;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RestExceptionMessage {
    private String message;
    private List<String> errors;
    private Date timestamp;

    public RestExceptionMessage(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
        this.timestamp = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
