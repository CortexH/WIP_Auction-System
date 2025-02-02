package com.demo.exceptions;

import com.demo.dto.output.GenericErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElement(NoSuchElementException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new GenericErrorDTO(LocalDateTime.now().toString(), 404, HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage())
        );
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<?> handleNotAuthorizedException(NotAuthorizedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new GenericErrorDTO(LocalDateTime.now().toString(), 401, HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage())
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflict(ConflictException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new GenericErrorDTO(LocalDateTime.now().toString(), 409, HttpStatus.CONFLICT.getReasonPhrase(), ex.getMessage())
        );
    }


}
