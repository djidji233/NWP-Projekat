package raf.edu.rs.projekat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        for (ObjectError error: exception.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }

        return errors;
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<HttpStatus> handleApiException(ApiException e, WebRequest request){
        return new ResponseEntity<>(e.getHttpStatus());
    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<HttpStatus> handleApiException(InterruptedException e, WebRequest request){
        System.out.println("Machine Thread sleep exception");
        return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
    }

    @ExceptionHandler(UnsupportedEncodingException.class)
    public ResponseEntity<HttpStatus> handleApiException(UnsupportedEncodingException e, WebRequest request){
        System.out.println("Token decoding error");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
