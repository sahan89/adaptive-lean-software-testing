package com.lean.exceptiondomain;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String dataNotFound) {
        super(dataNotFound);
    }
}
