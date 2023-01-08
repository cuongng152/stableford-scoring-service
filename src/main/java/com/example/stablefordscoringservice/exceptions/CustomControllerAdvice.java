package com.example.stablefordscoringservice.exceptions;

import com.example.stablefordscoringservice.service.coursescore.CourseScoreImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
class CustomControllerAdvice {


    Logger logger = LoggerFactory.getLogger(CourseScoreImplementation.class);

    private ResponseEntity<ErrorResponse> getErrorResponseResponseEntity(Exception e, HttpStatus status) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();
        logger.trace(stackTrace);
        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }
    @ExceptionHandler({CustomDataNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleCustomDataNotFoundExceptions(
            Exception e
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND; // 404

        // converting the stack trace to String
        return getErrorResponseResponseEntity(e, status);
    }

    @ExceptionHandler({ServerErrorException.class})
    public ResponseEntity<ErrorResponse> handleServerErrorException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        // converting the stack trace to String
        return getErrorResponseResponseEntity(e, status);
    }

    @ExceptionHandler({DataExistException.class})
    public ResponseEntity<ErrorResponse> handleDataExistException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400

        // converting the stack trace to String
        return getErrorResponseResponseEntity(e, status);
    }
}