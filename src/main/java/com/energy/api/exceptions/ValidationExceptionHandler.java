package com.energy.api.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, List<?>>> notValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(this::getErrorMessage)
                .collect(Collectors.toList());
        ArrayList<String> httpStatuses = new ArrayList<>();

        Map<String, List<?>> result = new HashMap<>();
        result.put("Http Status", List.of(HttpStatus.BAD_REQUEST.toString()));
        result.put("Errors", errors);

        return ResponseEntity.badRequest().body(result);
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, List<?>>> handlePersonAlreadyExists(PersonAlreadyExistsException personAlreadyExistsException) {
        List<String> errors = List.of(personAlreadyExistsException.getMessage());

        Map<String, List<?>> result = new HashMap<>();
        result.put("httpStatus", List.of(HttpStatus.BAD_REQUEST.toString()));
        result.put("errors", errors);

        return ResponseEntity.badRequest().body(result);
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;
            return fieldError.getField() + ": " + error.getDefaultMessage();
        }
        return error.getDefaultMessage();
    }
}
