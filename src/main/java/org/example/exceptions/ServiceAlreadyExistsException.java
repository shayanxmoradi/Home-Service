package org.example.exceptions;

public class ServiceAlreadyExistsException extends RuntimeException {
    public ServiceAlreadyExistsException(String message) {
        super(message);
    }
}
