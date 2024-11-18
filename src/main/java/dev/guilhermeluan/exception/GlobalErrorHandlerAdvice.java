package dev.guilhermeluan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalErrorHandlerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultErrorMessage> handleNotFoundException(NotFoundException e) {
        var error = new DefaultErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                e.getReason()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(error);
    }

    @ExceptionHandler(TaskNameAlreadyExists.class)
    public ResponseEntity<DefaultErrorMessage> handleTaskNameAlreadyExists(TaskNameAlreadyExists e) {
        var error = new DefaultErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getReason()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(error);
    }

    @ExceptionHandler(TaskCostNegative.class)
    public ResponseEntity<DefaultErrorMessage> handleTaskCostNegative(TaskCostNegative e) {
        var error = new DefaultErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getReason()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var error = new DefaultErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                Objects.requireNonNull(e.getFieldError()).getDefaultMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(error);
    }

}
