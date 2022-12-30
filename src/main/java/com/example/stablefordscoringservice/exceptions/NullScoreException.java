package com.example.stablefordscoringservice.exceptions;


public class NullScoreException extends RuntimeException {
    public NullScoreException() {
        super();
    }

    public NullScoreException(String message) {
        super(message);
    }
}
