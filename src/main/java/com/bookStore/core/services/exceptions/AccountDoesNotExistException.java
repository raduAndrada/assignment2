package com.bookStore.core.services.exceptions;

/**
 * Created by Andrada on 4/9/2017.
 */
public class AccountDoesNotExistException extends RuntimeException {
    public AccountDoesNotExistException() {
    }

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}

