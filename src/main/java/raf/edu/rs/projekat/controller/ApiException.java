package raf.edu.rs.projekat.controller;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private HttpStatus httpStatus;
    private String path;

    public ApiException(HttpStatus httpStatus, String msg, String path) {
        super(msg);
        this.httpStatus = httpStatus;
        this.path = path;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getPath(){
        return path;
    }

}
