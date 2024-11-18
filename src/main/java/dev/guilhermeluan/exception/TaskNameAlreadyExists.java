package dev.guilhermeluan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TaskNameAlreadyExists extends ResponseStatusException {

    public TaskNameAlreadyExists(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
