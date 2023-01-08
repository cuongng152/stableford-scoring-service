package com.example.stablefordscoringservice.exceptions;

public class DataExistException extends RuntimeException {
    public DataExistException(String message) {
        super(message);
    }
}
