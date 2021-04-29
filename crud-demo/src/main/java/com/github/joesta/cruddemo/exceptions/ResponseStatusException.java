package com.github.joesta.cruddemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ResponseStatusException extends HttpStatusCodeException {
    public ResponseStatusException(HttpStatus statusCode) {
        super(statusCode);
    }
}
