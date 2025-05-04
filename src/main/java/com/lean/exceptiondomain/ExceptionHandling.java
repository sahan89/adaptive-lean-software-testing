package com.lean.exceptiondomain;

import com.lean.exceptiondomain.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<HttpResponse> dataNotFoundException(DataNotFoundException e) {
        return this.createHttpResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
