package dev.guilhermeluan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TaskCostNegative extends ResponseStatusException {

    public TaskCostNegative(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
