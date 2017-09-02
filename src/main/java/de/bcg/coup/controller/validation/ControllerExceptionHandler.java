package de.bcg.coup.controller.validation;

import de.bcg.coup.service.exception.InvalidScooterCountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InvalidScooterCountException.class)
    public ResponseEntity invalidScooterCountException(InvalidScooterCountException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

}
